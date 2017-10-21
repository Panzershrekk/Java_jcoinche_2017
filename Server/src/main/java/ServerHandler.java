import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.Channel;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    private static final ChannelGroup channels = new DefaultChannelGroup("Containers", GlobalEventExecutor.INSTANCE);
    private static final GameManager gameMngr = new GameManager();


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            channel.write("A new player - " + incoming.remoteAddress() + " has joined\n");
            channel.flush();
        }
        System.out.println("[SERVER] - " + incoming.remoteAddress() + " has joined\n");
        channels.add(ctx.channel());
        gameMngr.addPlayer();
        gameMngr.addPlayerInVector(incoming.remoteAddress().toString());
        if (channels.size() == 4) {
            for (Channel channel : channels) {
                channel.write("Welcome all to JCoinche game. Let us begin the game\n This is your hand:");
                channel.flush();
                channel.write(gameMngr.distrib());
                channel.flush();
            }
            gameMngr.setGameStarted(true);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            channel.write("A player" + incoming.remoteAddress() + " has left\n");
            channel.flush();
        }
        System.out.println("A player " + incoming.remoteAddress() + " has left\n");
        if (channels.size() == 4 && gameMngr.isGameStarted() == true) {
            for (Channel channel : channels) {
                channel.write("A player left the game disconnecting from server\n");
                channel.flush();
            }
            channels.disconnect();
        }
        channels.remove(ctx.channel());
        gameMngr.removePlayer();
        gameMngr.removePlayerFromVector(incoming.remoteAddress().toString());

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("[INFO] - " + incoming.remoteAddress() + " - " + msg);
        for (Channel channel : channels) {
            channel.write(gameMngr.readAction(msg.toString().trim().replaceAll("\\s+", " ").toUpperCase(), incoming.remoteAddress().toString()));
            channel.flush();
        }
        System.out.println("Currently playing : " + gameMngr.getCurrentlyPlaying());
    }
}