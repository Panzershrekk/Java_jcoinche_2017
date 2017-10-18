import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ServerHandler extends SimpleChannelInboundHandler<String> {


    private static final ChannelGroup channels = new DefaultChannelGroup("Containers", GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel currentChannel = ctx.channel();
        System.out.println("[INFO] - " + currentChannel.remoteAddress() + " has joined\n");
        super.handlerAdded(ctx);
        channels.add(ctx.channel());

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel currentChannel = ctx.channel();
        System.out.println("[INFO] - " + currentChannel.remoteAddress() + " has left\n");
        super.handlerRemoved(ctx);
        channels.remove(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        /*Channel currentChannel = ctx.channel();
        System.out.println("[INFO] - " + currentChannel.remoteAddress() + " - " + msg);
        ctx.write("HELLO");
        currentChannel.write("[Server] - Success");
        currentChannel.flush();
        Channel incoming = channelHandlerContext.channel();*/
        for (Channel channel : channels) {
            //if (channel != incoming){
                System.out.println("[INFO] - " + channel.remoteAddress() + " - " + msg);
                channel.write("[Server] - Success");
                channel.flush();
                channel.write("[Message]" + msg + "\n");
            }
        }

}