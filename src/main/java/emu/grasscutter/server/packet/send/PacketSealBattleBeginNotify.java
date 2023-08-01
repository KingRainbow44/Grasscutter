package emu.grasscutter.server.packet.send;

import emu.grasscutter.game.world.SealBattle;
import emu.grasscutter.net.packet.*;
import emu.grasscutter.net.proto.SealBattleBeginNotifyOuterClass.SealBattleBeginNotify;

public final class PacketSealBattleBeginNotify extends BasePacket {
    public PacketSealBattleBeginNotify(SealBattle battle) {
        super(PacketOpcodes.SealBattleBeginNotify);

        this.setData(SealBattleBeginNotify.newBuilder()
            .setBattleType(battle.getType().toProto())
            .setSealEntityId(battle.getEntityId())
            .setCOBODMBALLN(battle.getRadius())
            .setNEBJNBBMOKB(battle.getMaxProgress()));
    }
}
