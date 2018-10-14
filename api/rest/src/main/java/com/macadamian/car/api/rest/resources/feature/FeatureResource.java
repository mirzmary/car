package com.macadamian.car.api.rest.resources.feature;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;
import com.macadamian.car.api.model.car.CarCreationRequestModel;
import com.macadamian.car.api.model.common.ResponseModel;
import com.macadamian.car.api.model.common.UserRole;
import com.macadamian.car.api.model.feature.FeatureCreationRequestModel;
import com.macadamian.car.api.model.feature.FeatureUpdateRequestModel;
import com.macadamian.car.api.rest.resources.car.CarResource;
import com.macadamian.car.facade.annotation.Token;
import com.macadamian.car.facade.car.CarFacade;
import com.macadamian.car.facade.feature.FeatureFacade;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * User: Mary Mirzoyan
 * Date: 10/14/18
 * Time: 11:16 PM
 */

@Path("/feature")
@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "Car resource", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
public class FeatureResource  {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeatureResource.class);

    @Autowired
    private FeatureFacade featureFacade;

    @POST
    @Path("/")
    @Token(userRole = UserRole.admin)
    @JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})
    public Response createFeature(final FeatureCreationRequestModel featureCreationRequestModel, @Context final HttpServletRequest requestContext){
        LOGGER.debug("Processing feature creation request - {}", featureCreationRequestModel);
        final ResponseModel responseModel = featureFacade.createFeature(featureCreationRequestModel);
        LOGGER.debug("Feature has been successfully created for - {}, response - {}", featureCreationRequestModel, responseModel);
        return Response.ok(responseModel).build();
    }

    @PUT
    @Path("/")
    @Token(userRole = UserRole.admin)
    @JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})
    public Response updateFeature(final FeatureUpdateRequestModel featureUpdateRequestModel, @Context final HttpServletRequest requestContext){
        LOGGER.debug("Processing feature update request - {}", featureUpdateRequestModel);
        final ResponseModel responseModel = featureFacade.updateFeature(featureUpdateRequestModel);
        LOGGER.debug("Feature has been successfully updated for - {}, response - {}", featureUpdateRequestModel, responseModel);
        return Response.ok(responseModel).build();
    }
}
