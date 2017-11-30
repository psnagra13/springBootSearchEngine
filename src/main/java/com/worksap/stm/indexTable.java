package com.worksap.stm;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Cluster.*;




public class indexTable {

	private  Cluster cluster;
	 
    private  Session session;
 
    public  void connect(String node, Integer port) {
        Builder b = Cluster.builder().addContactPoint(node);
        if (port != null) {
            b.withPort(port);
        }
        cluster = b.build();
 
        session = cluster.connect();
    }
 

    public void close() {
        session.close();
        cluster.close();
    }
	
	
	
    public void createKeyspace(
    		  String keyspaceName, String replicationStrategy, int replicationFactor) {
    		  StringBuilder sb = 
    		    new StringBuilder("CREATE KEYSPACE IF NOT EXISTS ")
    		      .append(keyspaceName).append(" WITH replication = {")
    		      .append("'class':'").append(replicationStrategy)
    		      .append("','replication_factor':").append(replicationFactor)
    		      .append("};");
    		         
    		    String query = sb.toString();
    		    session.execute(query);
    		}
	
	
	
	
	
	
	
	
	
	
}
