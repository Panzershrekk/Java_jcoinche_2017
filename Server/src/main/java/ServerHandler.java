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
                channel.write("Welcome all to JCoinche game. Let us begin the game\nType \"HAND\" to see your cards");
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
        channels.remove(ctx.channel());
        gameMngr.removePlayer();
        gameMngr.removePlayerFromVector(incoming.remoteAddress().toString());
        if (gameMngr.getNbrPlayer() < 4 && gameMngr.isGameStarted() == true) {
            for (Channel channel : channels) {
                channel.write("A player left the game disconnecting from server\n");
                channel.flush();
            }
            channels.disconnect();
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel incoming = ctx.channel();
        String clientMsg = "";
        System.out.println("[INFO] - " + incoming.remoteAddress() + " - " + msg);
        clientMsg = gameMngr.readAction(msg.toString().trim().replaceAll("\\s+", " ").toUpperCase(), incoming.remoteAddress().toString());
        for (Channel channel : channels) {
            if (channel == incoming && clientMsg.startsWith("INFO: ")) {
                channel.write(clientMsg);
                channel.flush();
            }
            else if (channel == incoming && clientMsg.startsWith("HAND: ")) {
                channel.write(clientMsg);
                channel.flush();
            }
            else if (clientMsg.startsWith("ACTION: ") == true) {
                channel.write(gameMngr.readAction(msg.toString().trim().replaceAll("\\s+", " ").toUpperCase(), incoming.remoteAddress().toString()));
                channel.flush();
            }
        }
        System.out.println("Currently playing : " + gameMngr.getCurrentlyPlaying());
    }


}