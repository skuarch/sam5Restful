package webservices.restful.resources.Link;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.wrappers.SendReceiveString;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import util.JSONUtil;

/**
 *
 * @author skuarch
 */
@Path("/jumper")
public class Jumper {

    private static final Logger logger = Logger.getLogger(Jumper.class);

    //==========================================================================    
    @POST
    @Path("/link")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String get(String json) {

        if (json == null || json.length() < 1 || !json.startsWith("{") || !json.endsWith("}")) {
            return JSONUtil.getJSONErrorAppend("incorrect json");
        }

        String host = null;
        int port = 0;
        int timeout = 0;
        String textReturned = null;
        JSONObject jSONObject;

        try {

            jSONObject = JSONUtil.stringToJSONObject(json);
            host = jSONObject.getString("host");
            port = Integer.parseInt(jSONObject.getString("port"));
            timeout = Integer.parseInt(jSONObject.getString("timeout"));

            SendReceiveString srs = new SendReceiveString(host, port, timeout);
            textReturned = srs.sendReceive(json);

        } catch (Exception e) {
            logger.error(e);
            return JSONUtil.getJSONErrorAppend(e.getMessage());
        }

        return textReturned;
    } // end get
} // end class