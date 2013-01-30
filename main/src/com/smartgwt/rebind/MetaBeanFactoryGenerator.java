/*
 * Smart GWT (GWT for SmartClient)
 * Copyright 2008 and beyond, Isomorphic Software, Inc.
 *
 * Smart GWT is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version 3
 * is published by the Free Software Foundation.  Smart GWT is also
 * available under typical commercial license terms - see
 * http://smartclient.com/license
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 */

package com.smartgwt.rebind;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.TreeLogger.Type;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.*;

import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

import com.smartgwt.client.bean.BeanFactory;
import com.smartgwt.client.widgets.BaseWidget;
import com.smartgwt.client.core.DataClass;
import com.smartgwt.rebind.BeanClass;

import java.io.PrintWriter;

public class MetaBeanFactoryGenerator extends Generator {
    public String getSimpleFactoryName (JClassType interfaceType) {
        StringBuilder builder = new StringBuilder();
        JClassType iterator = interfaceType;
        while (iterator != null) {
            if (iterator != interfaceType) builder.insert(0, "_");
            builder.insert(0, iterator.getSimpleSourceName());
            iterator = iterator.getEnclosingType();
        }
        builder.append("Impl");
        return builder.toString();
    }

    public String getFactoryPackage (JClassType interfaceType) {
        return interfaceType.getPackage().getName();
    }
    
    public String generate(TreeLogger logger, GeneratorContext context, String typeName) throws UnableToCompleteException {
        TypeOracle typeOracle = context.getTypeOracle();
        JClassType metaFactoryType = typeOracle.findType(typeName);
        
        final String genPackageName = getFactoryPackage(metaFactoryType);
        final String genClassName = getSimpleFactoryName(metaFactoryType);

        ClassSourceFileComposerFactory composer = new ClassSourceFileComposerFactory(genPackageName, genClassName);
        composer.addImplementedInterface(typeName);
        composer.addImport(com.smartgwt.client.bean.BeanFactory.class.getCanonicalName());

        PrintWriter printWriter = context.tryCreate(logger, genPackageName, genClassName);
        if (printWriter != null) {
            SourceWriter sourceWriter = composer.createSourceWriter(context, printWriter);
            sourceWriter.println("// This class lovingly generated by " + MetaBeanFactoryGenerator.class.getCanonicalName() + "\n");

            StringBuilder functions = new StringBuilder();

            // Our constructor ... will be called by GWT.create()
            sourceWriter.println(genClassName + " () {");
            sourceWriter.indent();

            JClassType beanFactoryType = typeOracle.findType(BeanFactory.class.getCanonicalName()).isClass();
            JClassType baseWidgetType = typeOracle.findType(BaseWidget.class.getCanonicalName()).isClass();
            JClassType dataClassType = typeOracle.findType(DataClass.class.getCanonicalName()).isClass();

            // Iterate over the methods defined on the interface
            for (JMethod method : metaFactoryType.getMethods()) {
                if (method.getParameters().length != 0) {
                    logger.log(Type.ERROR, typeName + "::" + method.getName() + " should have no parameters.");
                    throw new UnableToCompleteException();
                }

                JParameterizedType returnType = method.getReturnType().isParameterized();
                if (returnType == null) {
                    logger.log(Type.ERROR, typeName + "::" + method.getName() + " has a non-parameterized return type.");
                    throw new UnableToCompleteException();
                }

                if (returnType.getBaseType() != beanFactoryType) {
                    logger.log(Type.ERROR, typeName + "::" + method.getName() + " does not have BeanFactory<> as its return type.");
                    throw new UnableToCompleteException();                    
                }
               
                JClassType[] typeArgs = returnType.getTypeArgs();
                if (typeArgs.length != 1) {
                    logger.log(Type.ERROR, typeName + "::" + method.getName() + " should have a return type with one parameterized type.");
                    throw new UnableToCompleteException();
                }

                JClassType beanClassType = typeArgs[0];
                if (
                    !baseWidgetType.isAssignableFrom(beanClassType) &&
                    !dataClassType.isAssignableFrom(beanClassType)
                ) {
                    logger.log(Type.ERROR, typeName + "::" + method.getName() + ": for now, factories can only be created for Canvas or DataClass and subclasses.");
                    throw new UnableToCompleteException();
                }

                BeanClass beanClass = new BeanClass(beanClassType, typeOracle);
                beanClass.generateFactory(logger, context);
                    
                // We have to instantiate the factory to register it in the BeanFactory static API
                sourceWriter.println(beanClass.getQualifiedFactoryName() + ".create(false);");

                // And we'll need to generate the function!
                functions.append(
                    "\n\n@Override public BeanFactory<" + 
                    beanClassType.getQualifiedSourceName() + 
                    "> " + method.getName() + "() {\n  " +
                    "return (BeanFactory<" +
                    beanClassType.getQualifiedSourceName() + 
                    ">) BeanFactory.getFactory(" +
                    beanClassType.getQualifiedSourceName() + 
                    ".class);\n}"
                );
            }

            sourceWriter.outdent();
            sourceWriter.println("}");

            sourceWriter.println(functions.toString());
            sourceWriter.commit(logger);
        }

        return composer.getCreatedClassName();
    }
}
