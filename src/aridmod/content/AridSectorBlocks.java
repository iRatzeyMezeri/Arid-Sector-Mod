package aridmod.content;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.unit.MissileUnitType;
import mindustry.world.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.heat.*;
import mindustry.world.blocks.liquid.*;
import mindustry.world.blocks.logic.*;
import mindustry.world.blocks.payloads.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.blocks.units.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;
import static mindustry.type.ItemStack.*;
import aridmod.content.*;

public class AridSectorBlocks {
    public static Block
            // Environment
            // Ores
            wallZincOre, wallSiliconOre, groundPalladiumOre, wallPalladiumOre,
    // Floors
    navystone,
    // Walls
    navystoneWall,
    // Vents
    navystoneVent;
    // Turrets
    // Defense


    public static void load(){
        // Environment
        navystone = new Floor("navystone"){{
            variants = 3;
        }};
        navystoneWall = new StaticWall("navystone-wall"){{
            variants = 2;
        }};
        navystoneVent = new SteamVent("navystone-vent"){{
                parent = blendGroup = navystone;
                attributes.set(Attribute.steam, 1f);
        }};
}}