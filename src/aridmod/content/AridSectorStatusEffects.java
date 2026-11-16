package aridmod.content;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.units.StatusEntry;
import mindustry.gen.*;
import mindustry.graphics.Pal;
import mindustry.type.*;

public class AridSectorStatusEffects {
    public static StatusEffect
        safe;

    public static void load(){
        safe = new StatusEffect("safe"){{
            color = Pal.heal;
            speedMultiplier = 1.6f;
            healthMultiplier = 1.2f;
        }};
    }
}
