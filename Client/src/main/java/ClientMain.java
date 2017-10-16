import io.netty.channel.nio.NioEventLoopGroup;

public class ClientMain {
    public static void main(String[] args) {
        String message = "Robert";
        NioEventLoopGroup event;
        MessageUtil messageUtil = new MessageUtil(message);
        messageUtil.printMessage();
    }
}
