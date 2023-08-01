package emu.grasscutter.game.world;

import emu.grasscutter.game.entity.EntityMonster;
import emu.grasscutter.scripts.constants.*;
import emu.grasscutter.scripts.data.ScriptArgs;
import emu.grasscutter.server.packet.send.*;
import lombok.*;

@Data
@RequiredArgsConstructor
public final class SealBattle {
    private final Scene scene;
    private final int gadgetId;
    private final int entityId;

    private final int radius;
    private final int timer;
    private final int maxProgress;
    private final int groupId;

    private final SealBattleType type;

    private final int startTime
        = (int) (System.currentTimeMillis() / 1000);
    private final int endTime
        = this.getStartTime() + this.getTimer();

    private int progress;
    private boolean success;

    /**
     * Invoked when a monster dies.
     *
     * @param monster The monster that died.
     */
    public void onMonsterDeath(EntityMonster monster) {
        // Check if the battle type is monster-related.
        if (this.getType() != SealBattleType.KILL_MONSTER) return;
        // Check if the monster is in the seal battle.
        if (monster.getGroupId() != this.getGroupId()) return;

        this.progress++; // Add progress.
        this.getScene().broadcastPacket( // Notify the client.
            new PacketSealBattleProgressNotify(this));

        this.check(); // Check if the battle has finished.
    }

    /**
     * Checks if the battle has been finished.
     * Validates the progress and the time.
     */
    public void check() {
        // Check if the time has expired.
        if (System.currentTimeMillis() >= this.getEndTime() * 1000f) {
            this.success = false;
            this.finish();
            return;
        }

        // Check if the progress is enough.
        if (this.getProgress() >= this.getMaxProgress()) {
            this.success = true;
            this.finish();
        }
    }

    /**
     * Finishes the battle.
     */
    public void finish() {
        var scene = this.getScene();

        // Broadcast the finish packet.
        scene.broadcastPacket(
            new PacketSealBattleEndNotify(this));
        // Remove this battle from the scene.
        scene.getSealBattles().remove(this);
        // Trigger the quest event.
        scene.getScriptManager().callEvent(new ScriptArgs(
            this.getGroupId(), EventType.EVENT_SEAL_BATTLE_END,
            this.getGadgetId(), this.isSuccess() ? 1 : 0
        ));
    }
}
