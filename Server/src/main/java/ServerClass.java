
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ServerClass {
        public static void main(String[] args) throws Exception{
            ServerClass Srv = new ServerClass(Integer.parseInt("1234"));
            Srv.Launch();
        }

        private int port;

        ServerClass(int port) throws Exception
        {
            this.port = port;
            System.out.println("Port is = " + port);
        }

        public void Launch() {
            port = this.port;
            EventLoopGroup producer = new NioEventLoopGroup();
            EventLoopGroup consumer = new NioEventLoopGroup();

            try
            {
                ServerBootstrap bootstrap = new ServerBootstrap()
                        .group(producer, consumer)
                        .channel(NioServerSocketChannel.class)
                        .childHandler(new ChatServerInitializer());
                System.out.println("Server started");
                bootstrap.bind(port).sync().channel().closeFuture().sync();

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                producer.shutdownGracefully();
                consumer.shutdownGracefully();
            }

    }
}
