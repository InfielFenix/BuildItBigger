/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.projects.alexanderauer.jokingGCM;

import com.example.Joker;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myJokingApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "jokingGCM.alexanderauer.projects.com",
                ownerName = "jokingGCM.alexanderauer.projects.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    @ApiMethod(name = "getJoke")
    public MyBean getJoke(){
        // create a Joker object
        Joker joker = new Joker();

        // create a response object
        MyBean response = new MyBean();

        // put the joke into the response object
        response.setData(joker.getJoke());

        // send the joke back
        return response;
    }

}
