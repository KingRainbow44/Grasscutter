package emu.grasscutter.server.packet.send;

import emu.grasscutter.game.world.SealBattle;
import emu.grasscutter.net.packet.*;
import emu.grasscutter.net.proto.SealBattleProgressNotifyOuterClass.SealBattleProgressNotify;

public final class PacketSealBattleProgressNotify extends BasePacket {
    public PacketSealBattleProgressNotify(SealBattle battle) {
        super(PacketOpcodes.SealBattleProgressNotify);

        this.setData(SealBattleProgressNotify.newBuilder()
            .setProgress(battle.getProgress())
            .setEndTime(battle.getEndTime())
            .setNEBJNBBMOKB(battle.getMaxProgress())
            .setSealEntityId(battle.getEntityId())
            .setMCMKKEIHOKO(battle.getRadius()));
    }
}
