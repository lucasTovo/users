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
package orion.users;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.microshed.testing.testcontainers.ApplicationContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;

/**
 * This class creates the containers to run integration tests for Orion Talk Service
 */
public class DockerCompose implements BeforeAllCallback, AfterAllCallback {
    
    // Configure the a users service container from Dockerfile
    @Container
    public static ApplicationContainer users = new ApplicationContainer()
        .withAppContextRoot("/orion-users-service/users")
        .waitingFor(Wait.forHttp("/"))
        .withNetworkAliases("service")
        .withExposedPorts(9080);

    public static MySQLContainer<?> mysql;

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {

        // create a private network for the containers
        Network network =  Network.newNetwork();

        // Set network for users service
        users.withNetwork(network);
        users.withNetworkAliases("users");

        // create a MySQL container to work with users service
        mysql = new MySQLContainer<>("mysql:latest");
        mysql.withDatabaseName("orion-users-service");
        mysql.withUsername("orion-users-service");
        mysql.withPassword("orion-users-service");
        mysql.withNetwork(network);
        mysql.withNetworkAliases("db");
        mysql.withExposedPorts(3306);
        mysql.waitingFor(Wait.forListeningPort());
        
        //Start the containers
        users.start();
        mysql.start();
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        users.stop();
        mysql.stop();
    }
}