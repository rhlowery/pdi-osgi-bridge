/*! ******************************************************************************
 *
 * Pentaho Data Integration
 *
 * Copyright (C) 2002-2017 by Hitachi Vantara : http://www.pentaho.com
 *
 *******************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ******************************************************************************/

package org.pentaho.di.osgi;

import org.junit.Test;
import org.pentaho.di.core.lifecycle.LifecycleException;
import org.pentaho.di.osgi.registryExtension.OSGIPluginRegistryExtension;

/**
 * Created by bryan on 8/15/14.
 */
public class OSGIKettleLifecycleListenerTest {
  @Test(timeout = 500L)
  public void testOnEnvironmentInit() throws LifecycleException {
    OSGIKettleLifecycleListener lifecycleListener = new OSGIKettleLifecycleListener();
    new Thread( new Runnable() {
      @Override public void run() {
        try {
          Thread.sleep( 100 );
          OSGIKettleLifecycleListener.setDoneInitializing();
        } catch ( InterruptedException e ) {
          e.printStackTrace();
        }
      }
    } ).start();
    lifecycleListener.onEnvironmentInit();
  }

  @Test
  public void testOnEnvironmentShutdownNoop() {
    new OSGIPluginRegistryExtension();
    new OSGIKettleLifecycleListener().onEnvironmentShutdown();
  }
}
