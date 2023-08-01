package emu.grasscutter.server.packet.send;

import emu.grasscutter.game.world.SealBattle;
import emu.grasscutter.net.packet.*;
import emu.grasscutter.net.proto.SealBattleEndNotifyOuterClass.SealBattleEndNotify;

public final class PacketSealBattleEndNotify extends BasePacket {
    public PacketSealBattleEndNotify(SealBattle battle) {
        super(PacketOpcodes.SealBattleEndNotify);

        this.setData(SealBattleEndNotify.newBuilder()
            .setSealEntityId(battle.getEntityId())
            .setIsWin(battle.isSuccess()));
    }
}
