package hash_table;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8005)
                .usePlaintext()
                .build();

        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);

        HelloProto.HelloResponse response = stub.sayHello(HelloProto.HelloRequest.newBuilder().setName("Alice").build());

        System.out.println("Received: " + response.getMessage());

        channel.shutdown();
    }
}
