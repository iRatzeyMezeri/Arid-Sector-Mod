package aridmod.content;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import aridmod.ASPal;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.liquid.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
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
    basicRoadway, roadwayJunction, roadwayRouter, roadwayOverflow, roadwayUnderflow, roadwayBridge, roadwayUnloader,
    // Mining
    clayGrinder, hydrocarbonBore, clayExtractor,
    // Power
    ventDelver, aluminiumNode, aluminiumBattery, aluminiumDiode,
    // Liquid
    ventRig, drainway, drainwayJunction, drainwayRouter, drainwayBridge,
    // Turrets
    amp, atmos, centigrade, hertz,
    // Crafting
    biomaterialRestorationBed,
    // Defense
    brittleWall, largeBrittleWall, carapaceWall, largeCarapaceWall,
    // Payload
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
        basicRoadway = new StackConveyor("basic-roadway"){{
            requirements(Category.distribution, with(AridSectorItems.itemClay, 2));
            health = 40;
            speed = 4f / 60f;
            itemCapacity = 4;
        }};
        roadwayJunction = new Junction("roadway-junction"){{
            requirements(Category.distribution, with(AridSectorItems.itemClay, 4, Items.silicon, 2));
            speed = 4.5f;
            capacity = 1;
            health = 20;
            buildCostMultiplier = 3f;
            researchCost = with(AridSectorItems.itemClay, 15);
        }};

        roadwayRouter = new DuctRouter("roadway-router"){{
            requirements(Category.distribution, with(AridSectorItems.itemClay, 10, Items.silicon, 4));
            health = 60;
            speed = 3f;
            regionRotated1 = 1;
            solid = false;
            buildCostMultiplier = 3f;
            researchCost = with(AridSectorItems.itemClay, 15);
        }};

        roadwayOverflow = new OverflowDuct("overflow-roadway"){{
            requirements(Category.distribution, with(AridSectorItems.itemClay, 10, Items.silicon, 4));
            health = 80;
            speed = 4f;
            solid = false;
            buildCostMultiplier = 3f;
            researchCost = with(AridSectorItems.itemClay, 30, Items.silicon, 10);
        }};

        roadwayUnderflow = new OverflowDuct("underflow-roadway"){{
            requirements(Category.distribution, with(AridSectorItems.itemClay, 10, Items.silicon, 4));
            health = 80;
            speed = 4f;
            solid = false;
            invert = true;
            buildCostMultiplier = 3f;
            researchCost = with(AridSectorItems.itemClay, 30, Items.silicon, 10);
        }};

        roadwayBridge = new DuctBridge("roadway-bridge"){{
            requirements(Category.distribution, with(AridSectorItems.itemClay, 10, Items.silicon, 10));
            health = 120;
            speed = 7f;
            buildCostMultiplier = 3f;
            researchCost = with(AridSectorItems.itemClay, 30, Items.silicon, 30);
            crushFragile = true;
        }};

        roadwayUnloader = new DirectionalUnloader("roadway-unloader"){{
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
            drillTime = 1200;
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

            drillTime = 480f;
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
        aluminiumNode = new PowerNode("aluminium-node"){{
            requirements(Category.power, with(AridSectorItems.aluminium, 3));
            maxNodes = 12;
            laserRange = 8;
            underBullets = true;
            crushFragile = true;
        }};

        aluminiumBattery = new Battery("aluminium-battery"){{
            requirements(Category.power, with(AridSectorItems.aluminium, 5, Items.silicon, 2));
            consumePowerBuffered(2400f);
            baseExplosiveness = 1f;
        }};

        aluminiumDiode = new PowerDiode("aluminium-diode"){{
            requirements(Category.power, with(AridSectorItems.aluminium, 5, Items.silicon, 2));
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

        drainway = new Conduit("drainway"){{
            requirements(Category.liquid, with(Items.silicon, 1));
            botColor = Color.valueOf("322d3a");
            leaks = true;
            liquidCapacity = 50f;
            liquidPressure = 1.03f;
            health = 40;
            underBullets = true;
            explosivenessScale = flammabilityScale = 20f/50f;
        }};


        drainwayJunction = new LiquidJunction("drainway-junction"){{
            requirements(Category.liquid, with(Items.silicon, 3));
            health = 70;
            ((Conduit)drainway).junctionReplacement = this;
            solid = false;
            underBullets = true;
        }};

        drainwayBridge = new DirectionLiquidBridge("drainway-bridge"){{
            requirements(Category.liquid, with(Items.silicon, 8, AridSectorItems.aluminium, 3));
            range = 4;
            hasPower = false;
            liquidCapacity = 40f;
            underBullets = true;
            health = 70;
            explosivenessScale = flammabilityScale = 20f/120f;
            floating = true;

            ((Conduit)drainway).rotBridgeReplacement = this;
        }};

        drainwayRouter = new LiquidRouter("drainway-router"){{
            requirements(Category.liquid, with(Items.silicon, 6));
            liquidCapacity = 80f;
            liquidPadding = 3f/4f;
            underBullets = true;
            solid = false;
            health = 70;

            explosivenessScale = flammabilityScale = 40f/150f;
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
                        hitColor = backColor = trailColor = ASPal.electricLM;
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
                                    lightColor = ASPal.electricLM;
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
                        -3, -3f, 0,
                        3, -3f, 0
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
                            moveRot = -8f;
                            mirror = true;
                        }}
                );
            }};
            outlineColor = ASPal.resilientD;
            size = 3;
            reload = 70f;
            recoil = 2f;
            range = 120;
            health = 520;
            rotateSpeed = 1.5f;
            researchCostMultiplier = 0.05f;

            coolant = consume(new ConsumeLiquid(Liquids.water, 15f / 60f));
        }};

        atmos = new ItemTurret("atmos"){{
            requirements(Category.turret, with(AridSectorItems.carapace, 80, Items.silicon, 160));
            Effect sfe = new MultiEffect(Fx.shootSmokeSquare);
            ammo(
                    Items.silicon, new ArtilleryBulletType(3f, 12){{
                        width = 12f;
                        hitSize = 12f;
                        height = 18f;
                        lifetime = 52f;
                        shootEffect = sfe;
                        homingPower = 0.05f;
                        homingRange = 80f;
                        smokeEffect = Fx.shootBigSmoke;
                        ammoMultiplier = 1;
                        collidesAir = false;
                        pierce = false;
                        hitColor = backColor = trailColor = Color.valueOf("474a5c");
                        frontColor = Color.valueOf("767a84");
                        trailWidth = 3f;
                        trailLength = 10;
                        splashDamage = 96;
                        splashDamageRadius = 36;
                        hitEffect = despawnEffect = new MultiEffect(
                                new Effect(50f, 100f, e -> {
                                    float rad = splashDamageRadius;
                                    Color ecol = frontColor;
                                    e.scaled(7f, b -> {
                                        color(ecol, b.fout());
                                        Fill.circle(e.x, e.y, rad);
                                    });

                                    color(ecol);
                                    stroke(e.fout() * 3f);
                                    Lines.circle(e.x, e.y, rad);

                                    int points = 3;
                                    for(int i = 0; i < points; i++){
                                        float angle = i* 360f / points;
                                        //for(int s : Mathf.zeroOne){
                                        Drawf.tri(e.x + Angles.trnsx(angle, rad), e.y + Angles.trnsy(angle, rad), 6f, 6f * e.fout(), angle/* + s*180f*/);
                                        //}
                                    }

                                    Fill.circle(e.x, e.y, 12f * e.fout());
                                    color();
                                    Fill.circle(e.x, e.y, 6f * e.fout());
                                    Drawf.light(e.x, e.y, rad * 1.6f, ecol, e.fout());
                                }),
                                Fx.hitBulletColor
                        );
                        buildingDamageMultiplier = 0.3f;
                        hitSound = despawnSound = Sounds.explosionPlasmaSmall;
                    }},
                    AridSectorItems.carapace, new ArtilleryBulletType(3f, 20){{
                        width = 12f;
                        hitSize = 12f;
                        height = 18f;
                        lifetime = 60f;
                        rangeChange = 24f;
                        shootEffect = sfe;
                        smokeEffect = Fx.shootBigSmoke;
                        ammoMultiplier = 1;
                        pierce = false;
                        hitColor = backColor = trailColor = ASPal.biomaterialLM;
                        frontColor = ASPal.biomaterialL;
                        trailWidth = 3f;
                        trailLength = 10;
                        splashDamage = 134;
                        collidesAir = false;
                        splashDamageRadius = 24;
                        hitEffect = despawnEffect = new MultiEffect(
                                new Effect(50f, 100f, e -> {
                                    float rad = splashDamageRadius;
                                    Color ecol = frontColor;
                                    e.scaled(7f, b -> {
                                        color(ecol, b.fout());
                                        Fill.circle(e.x, e.y, rad);
                                    });

                                    color(ecol);
                                    stroke(e.fout() * 3f);
                                    Lines.circle(e.x, e.y, rad);

                                    int points = 6;
                                    for(int i = 0; i < points; i++){
                                        float angle = i* 360f / points;
                                        //for(int s : Mathf.zeroOne){
                                        Drawf.tri(e.x + Angles.trnsx(angle, rad), e.y + Angles.trnsy(angle, rad), 6f, 6f * e.fout(), angle/* + s*180f*/);
                                        //}
                                    }

                                    Fill.circle(e.x, e.y, 12f * e.fout());
                                    color();
                                    Fill.circle(e.x, e.y, 6f * e.fout());
                                    Drawf.light(e.x, e.y, rad * 1.6f, ecol, e.fout());
                                }),
                                Fx.hitBulletColor
                        );
                        buildingDamageMultiplier = 0.3f;
                        hitSound = despawnSound = Sounds.explosionPlasmaSmall;
                    }}
            );

            coolantMultiplier = 15f;
            shootSound = Sounds.door;
            shake = 1f;
            ammoPerShot = 3;
            drawer = new DrawTurret("resilient-"){{
                parts.addAll(
                        new RegionPart("-side"){{
                            progress = PartProgress.warmup;
                            under = true;
                            moveY = 3f;
                            mirror = true;
                        }}
                );
            }};
            shootY = -4;
            outlineColor = ASPal.resilientD;
            size = 3;
            reload = 70f;
            recoil = 2f;
            range = 156;
            health = 720;
            rotateSpeed = 1.2f;
            targetAir = false;

            coolant = consume(new ConsumeLiquid(Liquids.water, 15f / 60f));
        }};
        // Crafting
        biomaterialRestorationBed = new GenericCrafter("biomaterial-reconstruction-bed"){{
            requirements(Category.crafting, with(Items.silicon, 80, AridSectorItems.itemClay, 60, AridSectorItems.aluminium, 80));
            hasItems = true;
            liquidCapacity = 60f;
            craftTime = 120f;
            outputItem = new ItemStack(AridSectorItems.carapace, 2);
            size = 3;
            health = 240;
            squareSprite = false;
            hasPower = hasLiquids = true;
            craftEffect = Fx.circleColorSpark;
            updateEffect = Fx.smeltsmoke;
            drawer = new DrawMulti(new DrawRegion("-bottom"),
                    new DrawBubbles(ASPal.biomaterialM){{
                        sides = 10;
                        recurrence = 3f;
                        spread = 6;
                        radius = 1.5f;
                        amount = 20;
                    }},
                    new DrawDefault(),
                    new DrawGlowRegion("-glow"){{
                        color = ASPal.biomaterialX;
                        alpha = 0.5f;
                        glowIntensity = 0.3f;
                        glowScale = 6f;
                    }});

            consumeLiquid(Liquids.oil, 18f/60f);
            consumePower(180f/60f);
            consumeItem(Items.silicon, 5);
        }};
        // Payload
        // Defense
        brittleWall = new Wall("brittle-wall"){{
            requirements(Category.defense, with(AridSectorItems.itemClay, 8));
            health = 60;
            variants = 5;
        }};
        largeBrittleWall = new Wall("large-brittle-wall"){{
            requirements(Category.defense, with(AridSectorItems.itemClay, 32));
            health = 240;
            size = 2;
        }};
        carapaceWall = new Wall("carapace-wall"){{
            requirements(Category.defense, with(AridSectorItems.carapace, 4));
            health = 180;
        }};
        largeCarapaceWall = new Wall("large-carapace-wall"){{
            requirements(Category.defense, with(AridSectorItems.carapace, 16));
            health = 720;
            size = 2;
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