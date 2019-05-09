package org.eclipse.leshan.client;

import org.eclipse.leshan.client.request.ServerIdentity;
import org.eclipse.leshan.client.resource.BaseInstanceEnabler;
import org.eclipse.leshan.core.model.ObjectModel;
import org.eclipse.leshan.core.node.LwM2mResource;
import org.eclipse.leshan.core.response.ReadResponse;
import org.eclipse.leshan.core.response.WriteResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LedSensor extends BaseInstanceEnabler {
    public boolean STATE = true;
    public static final int ON_OFF = 5850;
    private static final List<Integer> supportedResources = Arrays.asList(ON_OFF);

    public LedSensor() {

    }

    @Override
    public synchronized ReadResponse read(ServerIdentity identity, int resourceId) {
        switch (resourceId) {
            case ON_OFF:
                return ReadResponse.success(resourceId, STATE);
            default:
                return super.read(identity, resourceId);
        }
    }

    @Override
    public WriteResponse write(ServerIdentity identity, int resourceid, LwM2mResource value) {
        if (resourceid == ON_OFF) {
            STATE = (Boolean)value.getValue();
            changeLEDState(STATE);
            return WriteResponse.success();
        }
        else{
            return super.write(identity, resourceid, value);
        }
    }

    private void changeLEDState(Boolean state){
        try {
            if (state) {
                Runtime.getRuntime().exec("python led.py 1");
            } else {
                Runtime.getRuntime().exec("python led.py 0");
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public List<Integer> getAvailableResourceIds(ObjectModel model) {
        return supportedResources;
    }
}
