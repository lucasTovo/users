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

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ibm.websphere.security.jwt.Claims;
import com.ibm.websphere.security.jwt.InvalidBuilderException;
import com.ibm.websphere.security.jwt.InvalidClaimException;
import com.ibm.websphere.security.jwt.JwtBuilder;
import com.ibm.websphere.security.jwt.JwtException;

import orion.user.data.UserDAO;
import orion.user.util.JavaMailUtil;
import orion.user.model.User;

@RequestScoped
@Path("/api/v1/")
public class PublicService {

    @Inject
    private UserDAO userDAO;

    /**
     * Creates a new user in the database
     * 
     * @param name     The name of a user
     * @param email    The email of a user
     * @param password The password of a user
     * 
     * @return An user object
     */
    @POST
    @Path("/create")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public User createUser(@FormParam("name") final String name, @FormParam("email") final String email,
            @FormParam("password") final String password) throws WebApplicationException {

        final User usr = new User();

        // in quickMail method, email is checked, if it is equal to one that already
        // exists in the bank,
        // it does not create
        if (quickMail(email).equals(false)) {
            usr.setName(name);
            usr.setEmail(email);
            usr.setPassword(password);
            userDAO.create(usr);
            return usr;
        } else {
            String message = "The informed e-mail already exist in the service";
            throw new WebApplicationException(message, Response.Status.CONFLICT);
        }
    }

    @POST
    @Path("/forgot")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public String forgot(@FormParam("email") final String email) throws Exception {

        String mail;

        try {
            // check if there is a email in the database
            final User usr = userDAO.find("email", email);

            // generate the hash
            String hashcode = usr.setHash(userDAO.generateHash());

            // send email to user
            usr.getEmail().equals(email);
            JavaMailUtil.sendMail(email, hashcode);
            mail = "connection complete";

        } catch (NoResultException | JwtException | InvalidBuilderException | InvalidClaimException e) {
            mail = "failed to recover password, try again";
        }
        return mail;
    }

    @POST
    @Path("/retrieve")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public String retrieve(@FormParam("hash") final String hash, @FormParam("password") final String password)
            throws Exception {

        String mail;

        try {
            // check if there is a hash in the database
            final User usr = userDAO.find("hash", hash);
            // send email to user

            // if atribute users's hash is false, cancel change
            usr.getHash().equals(hash);

            usr.setPassword(password);
            userDAO.update(usr);
            mail = "complete!";
            usr.setHash(null);

        } catch (NoResultException e) {
            mail = "failed to recover password, try again";
        }
        return mail;
    }

    /**
     * Authenticates the user in the service
     * 
     * @param email    The user's e-mail
     * @param password The user's password
     * 
     * @return Generates a JWT (Json Web Token) or HTTP 404 if fail
     * @throws Exception
     */
    @POST
    @Path("/login")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String login(@FormParam("email") final String email, @FormParam("password") final String password)
            throws WebApplicationException {
        String jwt = null;
        String message = null;

        try {
            // check if password is correct
            final User user = userDAO.find("email", email);
            if (user != null && user.getPassword().equals(userDAO.MD5(password))) {
                // generates the token
                jwt = JwtBuilder.create("jwtBuilder").jwtId(true).claim(Claims.SUBJECT, user.getEmail())
                        .claim("email", user.getEmail()).claim("groups", "users").buildJwt().compact();
            } else {
                message = "The user provides a wrong e-mail or password";
                throw new WebApplicationException("The Wrong password", Response.Status.NOT_FOUND);
            }
        } catch (Exception e) {
            message = "The login method generates a DAO Exception";
            throw new WebApplicationException(message, Response.Status.NOT_FOUND);
        }
        return jwt;
    }

    /**
     * checks if exist email
     * 
     * @param email
     * @return
     */
    private Boolean quickMail(@FormParam("email") final String email) {
        Boolean message;
        try {
            // check if there is a email in the database
            final User usr = userDAO.find("email", email);
            usr.getEmail().equals(email);

            message = true;
        } catch (NoResultException e) {
            message = false;
        }
        return message;
    }

}
