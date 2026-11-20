package aridmod.content;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.Seq;
import mindustry.*;
import mindustry.ai.UnitStance;
import mindustry.ai.types.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.type.unit.ErekirUnitType;
import mindustry.type.weapons.*;
import aridmod.*;
import aridmod.ASPal.*;

public class AridSectorUnitTypes {

    public static UnitType
            //Walker
            serf,
            //Hover
            pioneer,
            //Aerial
            thriller,
            //Core
            tenant;

    public static void load() {
        serf = new UnitType("serf"){{
            researchCostMultiplier = 0.5f;
            constructor = MechUnit::create;
            speed = 0.6f;
            armor = 3;
            hitSize = 7f;
            health = 360;
            stepSoundVolume = 0.4f;
            outlineColor = ASPal.resilientD;
            range = 90f;

            weapons.add(new Weapon("arid-sector-serf-laser-rifle"){{
                reload = 36f;
                x = 4f;
                y = 0.5f;
                layerOffset = -0.0001f;
                top = false;
                shootSound = Sounds.shootAvert;
                shootY = 6f;
                recoil = 2f;
                alternate = false;
                mirror = true;
                faceTarget = true;
                outlineColor = ASPal.resilientD;

                bullet = new ShrapnelBulletType(){{
                    length = 90f;
                    damage = 14f;
                    width = 3f;
                    serrationLenScl = 2f;
                    serrationSpaceOffset = 6f;
                    serrationFadeOffset = 0f;
                    serrations = 2;
                    serrationWidth = 3f;
                    fromColor = ASPal.electricLM;
                    toColor = ASPal.electricM;
                    shootEffect = smokeEffect = Fx.sparkShoot;
                    hitColor = ASPal.electricM;
                    hitEffect = Fx.hitBulletColor;
                    pierceDamageFactor = 0.8f;
                }};
            }});
        }};

        pioneer = new ErekirUnitType("pioneer"){{
            constructor = ElevationMoveUnit::create;
            researchCostMultiplier = 0.5f;
            accel = 0.09f;
            drag = 0.09f;
            speed = 1.6f;
            rotateSpeed = 3f;
            canDrown = false;
            shadowElevation = 0.1f;
            moveSound = Sounds.loopExtract;
            moveSoundVolume = 0.25f;
            moveSoundPitchMin = 0.7f;
            moveSoundPitchMax = 1.5f;
            hitSize = 7f;
            health = 80;
            hovering = true;
            flying = false;
            outlineColor = ASPal.resilientD;
            range = 12f;
            faceTarget = true;
            fogRadius = 120f;

            abilities.add(new MoveEffectAbility(0f, -7f, ASPal.biomaterialX, Fx.missileTrailShort, 4f){{
                teamColor = false;
            }});

            parts.addAll(
                    new HoverPart(){{
                        x = 3f;
                        y = 1f;
                        mirror = true;
                        radius = 6f;
                        phase = 90f;
                        stroke = 2f;
                        layerOffset = -0.001f;
                        color = ASPal.biomaterialX;
                    }},
                    new HoverPart(){{
                        x = 0f;
                        y = -3f;
                        mirror = false;
                        radius = 6f;
                        phase = 90f;
                        stroke = 2f;
                        layerOffset = -0.001f;
                        color = ASPal.biomaterialX;
                    }}
            );

            weapons.add(new Weapon(" "){{
                reload = 30f;
                y = 0f;
                x = 0f;
                shootSound = Sounds.shootAlpha;
                shootY = 4f;
                shootX = 0f;
                mirror = false;
                faceTarget = true;

                bullet = new ShrapnelBulletType(){{
                    length = 16f;
                    shootEffect = hitEffect = despawnEffect =  new WaveEffect(){{
                        sizeFrom = 4f;
                        sizeTo = 0f;
                        colorFrom = lightColor = ASPal.biomaterialX;
                        colorTo = ASPal.biomaterialLM;
                    }};
                    damage = 12f;
                    width = 4f;
                    serrations = 0;
                    layerOffset = -0.001f;
                    fromColor = ASPal.biomaterialX;
                    toColor = ASPal.biomaterialLM;
                    hitColor = ASPal.biomaterialX;
                    hitEffect = Fx.hitBulletColor;
                    pierce = false;
                }};
            }});

        }};

        thriller = new UnitType("thriller"){{
            aiController = SuicideAI::new;

            speed = 1f;
            hitSize = 12f;
            health = 40;
            range = 20f;
            flying = true;
            outlineColor = ASPal.resilientD;

            setEnginesMirror(
                    new UnitEngine(18 / 4f, -18 / 4f, 2.2f, 45f)
            );

            weapons.add(new Weapon(){{
                shootOnDeath = true;
                targetUnderBlocks = false;
                reload = 24f;
                shootCone = 180f;
                ejectEffect = Fx.none;
                shootSound = Sounds.explosionAfflict;
                shootSoundVolume = 0.5f;
                x = shootY = 0f;
                mirror = false;
                bullet = new BulletType(){{
                    collidesTiles = false;
                    collides = false;

                    rangeOverride = 40f;
                    hitEffect = Fx.shootSmokeSmite;
                    speed = 0f;
                    splashDamageRadius = 66f;
                    instantDisappear = true;
                    splashDamage = 360f;
                    buildingDamageMultiplier = 0.4f;
                    killShooter = true;
                    hittable = false;
                    collidesAir = true;
                    fragBullets = 3;
                    fragOnHit = false;
                    fragOnDespawn = true;
                }};
            }});
        }};
        tenant = new ErekirUnitType("tenant") {{
            coreUnitDock = true;

            range = 80f;
            faceTarget = true;
            targetPriority = -2;
            lowAltitude = false;
            mineWalls = true;
            mineFloor = true;
            mineHardnessScaling = false;
            flying = true;
            mineSpeed = 3f;
            mineTier = 2;
            buildSpeed = 2f;
            drag = 0.08f;
            speed = 4f;
            rotateSpeed = 7f;
            accel = 0.09f;
            itemCapacity = 80;
            health = 80f;
            armor = 1f;
            hitSize = 8f;
            engineSize = 0;
            outlineColor = ASPal.resilientD;

            fogRadius = 0f;
            targetable = true;
            hittable = true;

            setEnginesMirror(
                    new UnitEngine(18 / 4f, 16 / 4f, 2.2f, 45f),
                    new UnitEngine(20 / 4f, -18 / 4f, 2.2f, 315f)
            );

            weapons.add(new RepairBeamWeapon() {{
                widthSinMag = 0.11f;
                reload = 20f;
                x = 4f;
                y = 6.5f;
                rotate = false;
                shootY = 0f;
                beamWidth = 0.7f;
                repairSpeed = 0.5f;
                fractionRepairSpeed = 0.02f;
                aimDst = 0f;
                shootCone = 15f;
                mirror = true;

                targetUnits = false;
                targetBuildings = true;
                autoTarget = false;
                controllable = true;
                laserColor = Pal.accent;
                healColor = Pal.accent;

                bullet = new BulletType() {{
                    maxRange = 80f;
                }};
            }});
        }};
    }
};