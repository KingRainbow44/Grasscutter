package emu.grasscutter.scripts.constants;

import emu.grasscutter.net.proto.SealBattleTypeOuterClass;
import it.unimi.dsi.fastutil.ints.*;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum SealBattleType {
    NONE(0),
    ENERGY_CHARGE(1),
    KILL_MONSTER(2);

    private static Int2ObjectMap<SealBattleType> map = new Int2ObjectOpenHashMap<>();

    /**
     * @param value The value of the enum.
     * @return The enum corresponding to the given value.
     */
    public static SealBattleType valueOf(int value) {
        return map.get(value);
    }

    static {
        Arrays.stream(SealBattleType.values())
            .forEach(value -> map.put(value.value, value));
    }

    private final int value;

    /**
     * @return The protobuf representation of this enum.
     */
    public SealBattleTypeOuterClass.SealBattleType toProto() {
        return SealBattleTypeOuterClass.SealBattleType.forNumber(this.value);
    }
}
