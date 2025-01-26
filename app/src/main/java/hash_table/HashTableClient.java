package hash_table;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class HashTableClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8005)
                .usePlaintext()
                .build();

        HashTableServiceGrpc.HashTableServiceBlockingStub stub = HashTableServiceGrpc.newBlockingStub(channel);

        // Insert a value to hashtable
        HashTable.PutReply putReply = stub.put(HashTable.PutRequest.newBuilder().setKey("hello").setValue("world").build());
        System.out.printf("Inserted Key Value: %s %s%n", putReply.getKey(), putReply.getValue());

        // Get value for key "hello" from hash table
        HashTable.GetReply getReply = stub.get(HashTable.GetRequest.newBuilder().setKey("hello").build());
        System.out.println("Get Value for Key " + putReply.getKey() + ": " + getReply.getValue());

        // Get value for key that does not exist from hash table
        HashTable.GetReply nonExistentGetReply = stub.get(HashTable.GetRequest.newBuilder().setKey("random-key").build());
        System.out.println("Get Value for Key " + "random-key" + ": " + nonExistentGetReply.getValue());

        channel.shutdown();
    }
}
