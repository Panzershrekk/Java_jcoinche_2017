import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ClientMain {

    String host;
    int port;
    int containerPort;

    public ClientMain(String server, int port, int containerPort) {
        this.host = server;
        this.port = port;
        this.containerPort = containerPort;
    }

    public static void main(String[] args) {
        int containerPort = 8094;
        new ClientMain(args[0].toString(), Integer.valueOf(args[1]), containerPort).start();
    }

    public void start() {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap().group(group).channel(NioSocketChannel.class).handler(new ClientInitializer());

            Channel channel = bootstrap.connect(host, port).sync().channel();

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                channel.write(in.readLine() + "\r\n");
                channel.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}