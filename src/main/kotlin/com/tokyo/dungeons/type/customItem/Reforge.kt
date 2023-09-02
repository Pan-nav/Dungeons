package com.tokyo.dungeons.type.customItem

enum class Reforge(val stats: Stats) { // Stat multiplier
    FAIR(Stats(
        1.3,
    )),
    SHARP(Stats()),
    STRONG(Stats()),
    SUPERIOR(Stats()),
    UNREAL(Stats()),
    LEGENDARY(Stats()),
    WEAK(Stats()),
    FINE(Stats()),
    FEIRCE(Stats()),
}