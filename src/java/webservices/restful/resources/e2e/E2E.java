package webservices.restful.resources.e2e;

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
@Path("/e2e")
public class E2E {
    
    private final Logger logger = Logger.getLogger(E2E.class);

    //==========================================================================
    public E2E() {
    }

    //==========================================================================
    @Path("/hops")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String post(String json) {
        
        if (json == null || json.length() < 1) {
            return JSONUtil.getJSONErrorAppend("icorrect json");
        }
        
        String host = null;
        int port = 0;
        String textReturned = null;
        JSONObject jSONObject;
        
        try {
            
            jSONObject = JSONUtil.stringToJSONObject(json);
            host = jSONObject.getString("host");
            port = Integer.parseInt(jSONObject.getString("port"));
            
            SendReceiveString srs = new SendReceiveString(host, port, 90000);
            textReturned = srs.sendReceive(json);            
            
        } catch (Exception e) {            
            logger.error(e + " " + host + " port:" + port);
            return JSONUtil.getJSONErrorAppend(e.getMessage() + " host:" + host + " port:" + port);
        }
        
        return textReturned;
    } // end post
} // end E2E