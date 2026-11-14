package aridmod.content;

import arc.graphics.*;
import mindustry.content.*;
import mindustry.entities.Effect;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;
import static mindustry.type.ItemStack.*;

public class AridSectorBlocks {
    public static Block
            // Environment
    // Floors
    navystone, flint, gravel, clay, roughClay, clumpSoil, decayedSand, decayedDust,
    // Walls
    navystoneWall, flintWall, clayWall, clumpSoilWall, decayedSandWall,
    // Boulders
    navystoneBoulder, flintBoulder, clayBoulder, clumpSoilBoulder, siliconRemains,
    // Vents
    navystoneVent, flintVent, clayVent, decayedVent,
    // Ores
    flintSilicon, soilSilicon, aluminiumWallOre, groundCobaltOre, wallCobaltOre,
            // Buildable
    // Distribution
    resilientDuct, resilientJunction, resilientRouter, resilientOverflow, resilientUnderflow, resilientBridge, resilientUnloader,
    // Mining
    clayGrinder, hydrocarbonBore, clayExtractor,
    // Power
    ventDelver,
    // Liquid
    ventRig,
    // Turrets
    amp, atmos, centigrade, hertz,
    // Units
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
        clumpSoil = new Floor("clump-soil"){{
            variants = 3;
        }};
        decayedSand = new Floor("decayed-sand"){{
            variants = 3;
        }};
        decayedDust = new Floor("decayed-dust"){{
            variants = 3;
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
        aluminiumWallOre = new OreBlock("magnesium-wall-ore",AridSectorItems.aluminium){{
            variants = 3;
            wallOre = true;
        }};
        clayWall = new StaticWall("clay-wall"){{
            variants = 2;
        }};
        clumpSoilWall = new StaticWall("clump-soil-wall"){{
            variants = 2;
        }};
        soilSilicon = new StaticWall("soil-silicon"){{
            variants = 2;
            itemDrop = Items.silicon;
        }};
        decayedSandWall = new StaticWall("decayed-sand-wall"){{
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
        clumpSoilBoulder = new Prop("clump-soil-boulder"){{
            variants = 2;
            clumpSoil.asFloor().decoration = this;
        }};
        siliconRemains = new Prop("silicon-remains"){{
            variants = 2;
            decayedSand.asFloor().decoration = this;
            decayedDust.asFloor().decoration = this;
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
        decayedVent = new SteamVent("decayed-vent"){{
            parent = blendGroup = decayedDust;
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
            requirements(Category.distribution, with(Items.silicon, 20, AridSectorItems.aluminium, 10));
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
            requirements(Category.production, with(AridSectorItems.itemClay, 40, AridSectorItems.aluminium, 15));
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

            drillTime = 250f;
            size = 2;
            attribute = Attribute.get("clay-concentration");
            output = AridSectorItems.itemClay;
            fogRadius = 2;
            researchCost = with(AridSectorItems.itemClay, 25);
            ambientSound = Sounds.loopDrill;
            ambientSoundVolume = 0.04f;
        }};
        // Power
        ventDelver = new ThermalGenerator("vent-delver"){{
            requirements(Category.power, with(AridSectorItems.itemClay, 30, AridSectorItems.aluminium, 8));
            attribute = Attribute.steam;
            group = BlockGroup.liquids;
            displayEfficiencyScale = 1f / 9f;
            minEfficiency = 9f - 0.0001f;
            powerProduction = 2f / 9f;
            displayEfficiency = false;
            generateEffect = Fx.turbinegenerate;
            effectChance = 0.04f;
            size = 3;
            ambientSound = Sounds.loopHum;
            ambientSoundVolume = 0.06f;

            drawer = new DrawMulti(new DrawDefault(), new DrawBlurSpin("-rotator", 0.6f * 9f){{blurThresh = 0.01f;}},  new DrawRegion("-top"));

            hasLiquids = false;
            fogRadius = 3;
            researchCost = with(AridSectorItems.itemClay, 30, AridSectorItems.aluminium, 8);
        }};
        // Liquid
        ventRig = new AttributeCrafter("vent-rig"){{
            requirements(Category.liquid, with(AridSectorItems.itemClay, 30, Items.silicon, 8));
            attribute = Attribute.steam;
            group = BlockGroup.liquids;
            minEfficiency = 9f - 0.0001f;
            baseEfficiency = 0f;
            displayEfficiency = false;
            craftEffect = Fx.turbinegenerate;
            drawer = new DrawMulti( new DrawDefault(), new DrawBlurSpin("-rotator", 6f), new DrawRegion("-top"));
            craftTime = 120f;
            size = 3;
            ambientSound = Sounds.loopHum;
            ambientSoundVolume = 0.06f;
            hasLiquids = true;
            boostScale = 1f / 9f;
            itemCapacity = 0;
            outputLiquid = new LiquidStack(Liquids.oil, 12f / 60f);
            liquidCapacity = 60f;
        }};
        // Turrets
        amp = new ItemTurret("amp"){{
            requirements(Category.turret, with(AridSectorItems.aluminium, 80, AridSectorItems.itemClay, 60, Items.silicon, 120));
            Effect sfe = new MultiEffect(Fx.shootBigColor, Fx.colorSparkBig);

            ammo(
                    AridSectorItems.aluminium, new BasicBulletType(6f, 25){{
                        width = 9f;
                        hitSize = 7f;
                        height = 14f;
                        lifetime = 20f;
                        shootEffect = sfe;
                        smokeEffect = Fx.shootBigSmoke;
                        ammoMultiplier = 1;
                        pierceCap = 3;
                        pierce = true;
                        pierceBuilding = true;
                        hitColor = backColor = trailColor = Color.valueOf("8dd9cb");
                        frontColor = Color.white;
                        trailWidth = 1.5f;
                        trailLength = 5;
                        hitEffect = despawnEffect = Fx.hitBulletColor;
                        buildingDamageMultiplier = 0.3f;
                        fragBullets = 2;
                        fragAngle = 90;
                        fragOnHit = true;
                        fragOnAbsorb = true;
                        fragBullet = new LightningBulletType(){{
                                damage = 7;
                                lightningLength = 5;
                                collidesAir = true;
                                buildingDamageMultiplier = 0.4f;
                                pierceCap = 5;

                                lightningType = new BulletType(0.0001f, 0f) {{
                                    lifetime = Fx.lightning.lifetime;
                                    hitEffect = despawnEffect = Fx.hitBulletColor;
                                    status = StatusEffects.shocked;
                                    hittable = false;
                                    lightColor = Color.valueOf("8dd9cb");
                                    collidesAir = true;
                                    buildingDamageMultiplier = 0.4f;
                                    shieldDamageMultiplier = 0.2f;
                                }};
                        }};
                    }}
            );

            coolantMultiplier = 15f;
            shootSound = Sounds.shootLancer;

            targetUnderBlocks = false;
            shake = 1f;
            ammoPerShot = 2;
            shoot = new ShootBarrel(){{
                barrels = new float[]{
                        -3, 5f, 0,
                        3, 5f, 0
                };
                shots = 2;
            }};
            drawer = new DrawTurret("resilient-"){{
                parts.addAll(
                        new RegionPart("-barrel"){{
                            progress = PartProgress.recoil;
                            under = false;
                            moveY = -1.25f;
                            heatProgress = PartProgress.heat;
                            heatColor = Pal.turretHeat;
                        }},
                        new RegionPart("-blade"){{
                            progress = PartProgress.warmup;
                            under = false;
                            moveRot = 12f;
                            mirror = true;
                        }}
                );
            }};
            shootY = -2;
            outlineColor = Color.valueOf("1f1c24");
            size = 3;
            reload = 70f;
            recoil = 2f;
            range = 120;
            health = 520;
            rotateSpeed = 1.5f;
            researchCostMultiplier = 0.05f;

            coolant = consume(new ConsumeLiquid(Liquids.water, 15f / 60f));
        }};
        // Units
        // Defense
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