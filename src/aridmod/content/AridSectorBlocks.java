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
    // Floors
    navystone, flint, gravel, clay, roughClay,
    // Walls
    navystoneWall, flintWall, clayWall,
    // Boulders
    navystoneBoulder, flintBoulder, clayBoulder,
    // Vents
    navystoneVent, flintVent, clayVent,
    // Ores
    flintSilicon, magnesiumWallOre, groundPalladiumOre, wallPalladiumOre,
            // Buildable
    // Distribution
    resilientDuct, resilientJunction, resilientRouter, resilientOverflow, resilientUnderflow, resilientBridge, resilientUnloader,
    // Mining
    clayGrinder, hydrocarbonBore, clayExtractor,
    // Turrets
    // Defense
    // Effect
    coreOccupation;


    public static void load(){
        // Environment
        navystone = new Floor("navystone"){{
            variants = 3;
        }};
        flint = new Floor("flint"){{
            variants = 4;
        }};
        gravel = new Floor("gravel"){{
            variants = 2;
        }};
        clay = new Floor("clay"){{
            variants = 3;
        }};
        roughClay = new Floor("rough-clay"){{
            variants = 3;
            itemDrop = AridSectorItems.itemClay;
        }};
        navystoneWall = new StaticWall("navystone-wall"){{
            variants = 2;
        }};
        flintWall = new StaticWall("flint-wall"){{
            variants = 2;
        }};
        flintSilicon = new StaticWall("flint-silicon"){{
            variants = 2;
            itemDrop = Items.silicon;
        }};
        magnesiumWallOre = new OreBlock("magnesium-wall-ore",AridSectorItems.magnesium){{
            variants = 3;
            wallOre = true;
        }};
        clayWall = new StaticWall("clay-wall"){{
            variants = 2;
        }};
        navystoneBoulder = new Prop("navystone-boulder"){{
            variants = 2;
            navystone.asFloor().decoration = this;
        }};
        flintBoulder = new Prop("flint-boulder"){{
            variants = 2;
            flint.asFloor().decoration = this;
        }};
        clayBoulder = new Prop("clay-boulder"){{
            variants = 2;
            clay.asFloor().decoration = this;
        }};
        navystoneVent = new SteamVent("navystone-vent"){{
                parent = blendGroup = navystone;
                attributes.set(Attribute.steam, 1f);
        }};
        flintVent = new SteamVent("flint-vent"){{
            parent = blendGroup = flint;
            attributes.set(Attribute.steam, 1f);
        }};
        clayVent = new SteamVent("clay-vent"){{
            parent = blendGroup = clay;
            attributes.set(Attribute.steam, 1f);
        }};
        // Distribution
        resilientDuct = new Duct("transport-duct"){{
            requirements(Category.distribution, with(AridSectorItems.itemClay, 1));
            health = 50;
            speed = 3.5f;
            researchCost = with(AridSectorItems.itemClay, 5);
            junctionReplacement = resilientJunction;
            bridgeReplacement = resilientBridge;
        }};

        resilientJunction = new Junction("resilient-junction"){{
            requirements(Category.distribution, with(AridSectorItems.itemClay, 3));
            speed = 3;
            capacity = 1;
            health = 20;
            buildCostMultiplier = 3f;
            researchCost = with(AridSectorItems.itemClay, 15);
        }};

        resilientRouter = new DuctRouter("resilient-router"){{
            requirements(Category.distribution, with(AridSectorItems.itemClay, 10));
            health = 60;
            speed = 4f;
            regionRotated1 = 1;
            solid = false;
            buildCostMultiplier = 3f;
            researchCost = with(AridSectorItems.itemClay, 15);
        }};

        resilientOverflow = new OverflowDuct("resilient-overflow-duct"){{
            requirements(Category.distribution, with(AridSectorItems.itemClay, 10, Items.silicon, 2));
            health = 80;
            speed = 4f;
            solid = false;
            buildCostMultiplier = 3f;
            researchCost = with(AridSectorItems.itemClay, 30, Items.silicon, 10);
        }};

        resilientUnderflow = new OverflowDuct("resilient-underflow-duct"){{
            requirements(Category.distribution, with(AridSectorItems.itemClay, 10, Items.silicon, 2));
            health = 80;
            speed = 4f;
            solid = false;
            invert = true;
            buildCostMultiplier = 3f;
            researchCost = with(AridSectorItems.itemClay, 30, Items.silicon, 10);
        }};

        resilientBridge = new DuctBridge("resilient-bridge"){{
            requirements(Category.distribution, with(AridSectorItems.itemClay, 10, Items.silicon, 10));
            health = 120;
            speed = 4f;
            buildCostMultiplier = 3f;
            researchCost = with(AridSectorItems.itemClay, 30, Items.silicon, 30);
            crushFragile = true;
        }};

        resilientUnloader = new DirectionalUnloader("resilient-unloader"){{
            requirements(Category.distribution, with(Items.silicon, 20, AridSectorItems.magnesium, 10));
            health = 120;
            speed = 4f;
            solid = false;
            underBullets = true;
            regionRotated1 = 1;
            buildCostMultiplier = 3f;
            squareSprite = false;
        }};
        // Mining
        clayGrinder = new Drill("clay-grinder"){{
            requirements(Category.production, with(AridSectorItems.itemClay, 8));
            tier = 1;
            drillTime = 900;
            size = 2;
            researchCost = with(AridSectorItems.itemClay, 8);
        }};
        hydrocarbonBore = new BeamDrill("hydrocarbon-bore"){{
            requirements(Category.production, with(AridSectorItems.itemClay, 40, AridSectorItems.magnesium, 15));
            consumePower(0.5f);
            drillTime = 320f;

            tier = 3;
            size = 3;
            range = 6;
            fogRadius = 4;
            laserWidth = 0.7f;
            itemCapacity = 20;

            consumeLiquid(Liquids.oil, 2f / 60f);
        }};

        clayExtractor = new WallCrafter("clay-extractor"){{
            requirements(Category.production, with(AridSectorItems.itemClay, 25));

            drillTime = 350f;
            size = 2;
            attribute = Attribute.get("clay-concentration");
            output = AridSectorItems.itemClay;
            fogRadius = 2;
            researchCost = with(AridSectorItems.itemClay, 25);
            ambientSound = Sounds.loopDrill;
            ambientSoundVolume = 0.04f;
        }};
        // Effect
        coreOccupation = new CoreBlock("core-occupation"){{
            requirements(Category.effect, with(AridSectorItems.itemClay, 800, Items.silicon, 800, AridSectorItems.carapace, 360));
            alwaysUnlocked = true;

            isFirstTier = true;
            health = 960;
            size = 4;
            absorbLasers = true;
            itemCapacity = 2000;
            unitCapModifier = 6;
            squareSprite = false;
            unitType = AridSectorUnitTypes.tenant;
        }};

}}