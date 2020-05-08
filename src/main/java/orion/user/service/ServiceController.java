package orion.user.service;

// import java.util.Collection;
// import java.util.HashMap;
// import java.util.Map;
// import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import orion.user.data.UserDAO;
import orion.user.model.User;

import java.util.List;

import javax.inject.Inject;
//import javax.persistence.Id;
import javax.transaction.Transactional;




@Path("/service")
public class ServiceController {

    @Inject
    private UserDAO userDAO;


    @POST // create the user with name and email
    @Path("/create/{name}/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public User create(
        @PathParam("name") final String name,
        @PathParam("email") final String email) {


        final User usr = new User();
        usr.setName(name);
        usr.setEmail(email);
        userDAO.create(usr);
        return usr;
        
     }

    @GET //List all users
    @Path("/read/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<User> read(@PathParam("id") final long id) {
        return userDAO.read();
    }

     

    @DELETE // delete the user
    @Path("/delete/{id}")
    @Transactional
    public void delete(@PathParam("id") final long id) {

        userDAO.delete(id);
        
    }

    @GET
    @Path("/update/{id}/{name}/{email}")
    @Transactional
    public void update(@PathParam("id") final Long id, @PathParam("name") final String name
    , @PathParam("email") final String email) {
        User usr = userDAO.find(id);
        usr.setName(name);
        usr.setEmail(email);
        userDAO.update(usr);
    }



    



}
