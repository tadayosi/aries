/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.geronimo.blueprint.reflect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.geronimo.blueprint.mutable.MutableComponentMetadata;
import org.osgi.service.blueprint.reflect.ComponentMetadata;

/**
 * Implementation of ComponentMetadata
 *
 * @author <a href="mailto:dev@geronimo.apache.org">Apache Geronimo Project</a>
 * @version $Rev: 760378 $, $Date: 2009-03-31 11:31:38 +0200 (Tue, 31 Mar 2009) $
 */
public class ComponentMetadataImpl implements MutableComponentMetadata {

    protected String id;
    protected int initialization = ACTIVATION_EAGER;
    protected List<String> dependsOn;

    protected ComponentMetadataImpl() {
    }
    
    protected ComponentMetadataImpl(ComponentMetadata source) {
        id = source.getId();
        initialization = source.getActivation();
        dependsOn = new ArrayList<String>(source.getDependsOn());
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getActivation() {
        return initialization;
    }

    public void setInitialization(int initialization) {
        this.initialization = initialization;
    }

    public List<String> getDependsOn() {
        if (this.dependsOn == null) {
            return Collections.emptyList();
        } else {
            return Collections.unmodifiableList(this.dependsOn);
        }
    }

    public void setDependsOn(List<String> dependsOn) {
        this.dependsOn = dependsOn != null ? new ArrayList<String>(dependsOn) : null;
    }

    public void addDependsOn(String explicitDependency) {
        if (this.dependsOn == null) {
            this.dependsOn = new ArrayList<String>();
        }
        this.dependsOn.add(explicitDependency);
    }

    public void removeDependsOn(String dependency) {
        if (this.dependsOn != null) {
            this.dependsOn.remove(dependency);
        }
    }
}
