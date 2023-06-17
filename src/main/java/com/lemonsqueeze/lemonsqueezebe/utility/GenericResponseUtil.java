package com.lemonsqueeze.lemonsqueezebe.utility;

import java.io.IOException;

import org.springframework.http.HttpStatus;

import com.google.gson.Gson;
import com.lemonsqueeze.lemonsqueezebe.model.entity.generic.GenericResponse;
import com.lemonsqueeze.lemonsqueezebe.model.entity.generic.Meta;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;

public class GenericResponseUtil {
    public static GenericResponse getGenericResponse(HttpStatus status, Object object, String message) {
        GenericResponse response = new GenericResponse();
        response.setMeta(new Meta(status.toString(), message));
        response.setData(object);
        return response;
    }

    public static GenericResponse getGenericResponse(int status, Object object, String message) {
        GenericResponse response = new GenericResponse();
        response.setMeta(new Meta(Integer.toString(status), message));
        response.setData(object);
        return response;
    }

    public static void setGenericResponseToResponse(HttpServletResponse response, int status, String message) throws IOException, ServletException {
        GenericResponse genericResponse = getGenericResponse(status, null, message);
        String json = new Gson().toJson(genericResponse);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
        response.getWriter().flush();
    }

}
