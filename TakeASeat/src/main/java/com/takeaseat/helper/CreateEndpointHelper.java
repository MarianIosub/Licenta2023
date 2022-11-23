package com.takeaseat.helper;

public abstract class CreateEndpointHelper {
    public static String createEndpoint(String... endpoints) {
        StringBuilder endpointBuilder = new StringBuilder();
        for (String endpoint : endpoints) {
            endpointBuilder.append(endpoint);
        }
        return endpointBuilder.toString();
    }
}
