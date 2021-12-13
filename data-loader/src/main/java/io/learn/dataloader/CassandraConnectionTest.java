package io.learn.dataloader;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

public class CassandraConnectionTest {
    public static void main (String[] args) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Create the CqlSession object:
        try (CqlSession session = CqlSession.builder()
                .withCloudSecureConnectBundle(Paths.get("src/main/resources/secure-connect.zip"))
                .withAuthCredentials("EcnKaWsnEgCWlNdmGXnHqLfz", "H-td-EdHmh6R4Pg,s8s4zg8pB1+ZSgBZJ0pW37zFjBl5KfRy0tCEmig,Gw.,Rbk6fl0opH2rDyL13RDl7tjeu_+3atnGRnP+diyQtcs8AddcPJTD-Nxq6wMn9+EyjfQg")
                .build()) {
            // Select the release_version from the system.local table:
            ResultSet rs = session.execute("select release_version from system.local");
            Row row = rs.one();
            //Print the results of the CQL query to the console:
            if (row != null) {
                System.out.println(row.getString("release_version"));
            } else {
                System.out.println("An error occurred.");
            }
        }
        System.exit(0);
    }
}
