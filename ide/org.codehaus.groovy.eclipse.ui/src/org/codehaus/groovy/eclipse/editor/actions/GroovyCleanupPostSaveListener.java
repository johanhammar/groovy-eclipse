 /*
 * Copyright 2003-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.codehaus.groovy.eclipse.editor.actions;

import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.fix.ImportsCleanUp;
import org.eclipse.jdt.internal.ui.javaeditor.saveparticipant.IPostSaveListener;
import org.eclipse.jdt.ui.cleanup.ICleanUp;

/**
 * Sub class of {@link CleanUpPostSaveListener} so that we can use only
 * groovy-supported post-save cleanups
 * 
 * @author Andrew Eisenberg
 * @created Aug 17, 2009
 *
 */
public class GroovyCleanupPostSaveListener extends CleanUpPostSaveListener implements IPostSaveListener {

    /**
     * We only support organize imports for now.
     */
    @Override
    protected ICleanUp[] getCleanUps(Map settings, Set ids) {
        ICleanUp[] result= JavaPlugin.getDefault().getCleanUpRegistry().createCleanUps(ids);
        for (int i= 0; i < result.length; i++) {
            if (result[i] instanceof ImportsCleanUp) {
                GroovyImportsCleanup cleanup = new GroovyImportsCleanup(settings);
                return new ICleanUp[] { cleanup };
            }
        }

        return new ICleanUp[0];
    }
}