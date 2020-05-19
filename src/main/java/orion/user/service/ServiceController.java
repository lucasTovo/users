/**
 * @License
 * Copyright 2020 Orion Services
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package orion.user.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import orion.user.data.UserDAO;
import orion.user.model.User;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;



@RequestScoped
@Path("/api/v1.0/")
public class ServiceController {

    @Inject
    private UserDAO userDAO;


    @POST // create the user with name and email
    @Path("/createuser/")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public User create(
        @FormParam("name") final String name,
        @FormParam("email") final String email) {
        final User usr = new User();
            usr.setName(name);
            usr.setEmail(email);
            userDAO.create(usr);
            return usr; //return a User objetct
        
     }

    //  @GET // list users
    //  @Path("/listusers")
    //  @Consumes("application/x-www-form-urlencoded")
    //  @Produces(MediaType.APPLICATION_JSON)
    //  @Transactional
    //     public List<User> read(@PathParam("id") final long id) {
    //         return userDAO.read();
    // }

    @GET
    @Path("/listusers/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public User read(@PathParam("id") final long id) {
        final User usr = new User();
        return userDAO.find(id);
    }

     

    @POST // delete user
    @Path("/deleteuser")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public void delete(@FormParam("id") final long id) {
        userDAO.delete(id); // find the id and delete the user data
        
    }

    @POST // update user
    @Path("/updateuser")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public User update(
        @FormParam("id") final long id,
        @FormParam("name") final String name,
        @FormParam("email") final String email) {
            User usr = userDAO.find(id);
            usr.setName(name);
            usr.setEmail(email);
            userDAO.update(usr);
            return usr;
    }



    



}
