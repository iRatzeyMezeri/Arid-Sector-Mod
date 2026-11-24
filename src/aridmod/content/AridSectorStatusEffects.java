package aridmod.content;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import aridmod.ASPal;
import mindustry.content.*;
import mindustry.content.Fx.*;
import mindustry.entities.*;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.units.StatusEntry;
import mindustry.gen.*;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.type.*;

import static arc.graphics.g2d.Draw.color;

public class AridSectorStatusEffects {
    public static StatusEffect
        safe, dissolving, marked;

    public static void load(){
        safe = new StatusEffect("safe"){{
            color = Pal.heal.cpy().a(0.4f);
            speedMultiplier = 1.2f;
            healthMultiplier = 1.2f;
            outline = false;
            effect = Fx.hitSquaresColor;
            effectChance = 0.01f;
            damage = -0.05f;
        }};
        dissolving = new StatusEffect("dissolving"){{
            speedMultiplier = 0.6f;
            intervalDamageTime = 30f;
            intervalDamage = 48;
            color = ASPal.biomaterialL;
            effect = Fx.colorSpark;
            outline = false;
        }};
        marked = new StatusEffect("marked"){{
            intervalDamageTime = 30f;
            healthMultiplier = 0.8f;
            intervalDamage = 4;
            color = Color.valueOf("ff6e63");
            effectChance = 0.05f;
            effect = new MultiEffect(
                    new Effect(8, e -> {
                        color(e.color, Color.gray, e.fin());
                        float w = 1f + 4 * e.fout();
                        Drawf.tri(e.x, e.y, w, 15f * e.fout(), e.rotation);
                    }),
                    new Effect(8, e -> {
                        color(e.color, Color.gray, e.fin());
                        float w = 1f + 4 * e.fout();
                        Drawf.tri(e.x, e.y, w, 15f * e.fout(), e.rotation + 90f);
                    }),
                    new Effect(8, e -> {
                        color(e.color, Color.gray, e.fin());
                        float w = 1f + 4 * e.fout();
                        Drawf.tri(e.x, e.y, w, 15f * e.fout(), e.rotation + 90f + 90f);
                    }),
                    new Effect(8, e -> {
                        color(e.color, Color.gray, e.fin());
                        float w = 1f + 4 * e.fout();
                        Drawf.tri(e.x, e.y, w, 15f * e.fout(), e.rotation  + 90f + 90f + 90f);
                    })
                );
            outline = false;
        }};
    }
}
