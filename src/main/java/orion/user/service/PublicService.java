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
import javax.ws.rs.core.MediaType;

import com.ibm.websphere.security.jwt.Claims;
import com.ibm.websphere.security.jwt.InvalidBuilderException;
import com.ibm.websphere.security.jwt.InvalidClaimException;
import com.ibm.websphere.security.jwt.JwtBuilder;
import com.ibm.websphere.security.jwt.JwtException;

import orion.user.data.UserDAO;
import orion.user.model.User;

@RequestScoped
@Path("/api/v1.0/")
public class PublicService {

    @Inject
    private UserDAO userDAO;

    /**
     * Authenticates the user in the service
     * 
     * @param email    The user's e-mail
     * @param password The user`s password
     * @return If correct, generates a JWT (Json Web Token) or a fail message in
     *         String format
     */
    @POST
    @Path("/login")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String login(@FormParam("email") final String email, @FormParam("password") final String password) {
        String jwt = null;
        try {
            // check if there is a user in the database, it will create the jwt, otherwise
            // not
            final User user = userDAO.find("password", password, "email", email);

            // generates the token
            jwt = JwtBuilder.create("jwtBuilder").jwtId(true).claim(Claims.SUBJECT, user.getEmail())
                    .claim("email", user.getEmail()).claim("password", user.getPassword()).claim("groups", "users")
                    .buildJwt().compact();

        } catch (NoResultException | JwtException | InvalidBuilderException | InvalidClaimException e) {
            jwt = "User authentication fail. Please, check if the provided e-mail and password are correct";
        }
        return jwt;
    }

}
