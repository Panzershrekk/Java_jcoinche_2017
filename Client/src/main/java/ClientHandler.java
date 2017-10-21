import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends
        ChannelInboundHandlerAdapter {

    private static final Hand hand = new Hand();
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        //hand.readHand(msg.toString());
        System.out.println(msg);
    }
}