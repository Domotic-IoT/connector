package it.iot.server.Http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import it.iot.server.Converter.Bson.Measure;
import it.iot.server.DataMapper.MeasureInterface;
import it.iot.server.DataMapper.SimpleQuery;
import it.iot.server.Measure.AbstractMeasure;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servelet for handling measure search requests
 * 
 * @author Marco Zanella
 */
public class MeasureServlet extends HttpServlet {
    /**
     * Accepts a GET request
     * 
     * @param request  Request
     * @param response Response
     */
    protected void doGet(
        HttpServletRequest request, 
        HttpServletResponse response
    ) throws ServletException, IOException {
        MeasureInterface mapper = (MeasureInterface) getServletContext().getAttribute("mapper");
        Measure converter = new Measure();

        // Builds query
        SimpleQuery query = new SimpleQuery();
        Map<String, String[]> parameters = request.getParameterMap();
        if (parameters.containsKey("type")) {
            query.type(parameters.get("type")[0]);
        }
        if (parameters.containsKey("roomId")) {
            query.roomIdentifier(parameters.get("roomId")[0]);
        }
        if (parameters.containsKey("deviceId")) {
            query.deviceIdentifier(parameters.get("deviceId")[0]);
        }
        if (parameters.containsKey("fromTimestamp")) {
            query.fromTimestamp(Integer.parseInt(parameters.get("fromTimestamp")[0]));
        }
        if (parameters.containsKey("toTimestamp")) {
            query.toTimestamp(Integer.parseInt(parameters.get("toTimestamp")[0]));
        }

        // Retrieves documents
        Iterable<AbstractMeasure> measures = mapper.search(query);

        // Converts to JSON
        ArrayList<String> documents = new ArrayList<String>();
        for (AbstractMeasure measure: measures) {
            documents.add(converter.fromObject(measure).toJson());
        }

        // Serves result
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("[" + String.join(", ", documents) + "]");
    }
}
