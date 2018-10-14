package com.macadamian.car.api.rest.resources.car;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;
import com.macadamian.car.api.model.car.CarCreationRequestModel;
import com.macadamian.car.api.model.car.CarListingResponseModel;
import com.macadamian.car.api.model.common.ResponseListModel;
import com.macadamian.car.api.model.common.ResponseModel;
import com.macadamian.car.api.model.common.UserRole;
import com.macadamian.car.facade.annotation.Token;
import com.macadamian.car.facade.car.CarFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
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
 * Date: 10/13/18
 * Time: 12:20 AM
 */

@Path("/car")
@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "Car resource", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
public class CarResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarResource.class);

    @Autowired
    private CarFacade carFacade;

    @POST
    @Path("/")
    @Token(userRole = UserRole.user)
    @JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})
    public Response createCar(final CarCreationRequestModel carCreationRequestModel, @Context final HttpServletRequest requestContext){
        LOGGER.debug("Processing car creation request - {}", carCreationRequestModel);
        final ResponseModel responseModel = carFacade.createCar(carCreationRequestModel);
        LOGGER.debug("Car has been successfully created for - {}, response - {}", carCreationRequestModel, responseModel);
        return Response.ok(responseModel).build();
    }

    @GET
    @Path("/")
    public Response getAllCars(@Context final HttpServletRequest requestContext) {
        LOGGER.debug("Processing car list getting request");
        final ResponseListModel<CarListingResponseModel> responseModel = carFacade.getCarList();
        LOGGER.debug("Car list getting has been successfully processed, response - {}", responseModel);
        return Response.ok(responseModel).build();
    }
}
