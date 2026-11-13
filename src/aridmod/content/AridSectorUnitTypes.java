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

public class AridSectorUnitTypes {

    public static UnitType
            //Walker
            serf,
            //Core
            tenant;

    public static void load() {
        serf = new UnitType("serf"){{
            researchCostMultiplier = 0.5f;
            speed = 0.6f;
            hitSize = 7f;
            health = 220;
            stepSoundVolume = 0.4f;
            outlineColor = Color.valueOf("1f1c24");
            range = 120f;

            weapons.add(new Weapon("serf-laser-rifle"){{
                reload = 18f;
                x = 3.5f;
                y = 0.5f;
                top = false;
                shootSound = Sounds.shootLocus;
                shootY = 6f;
                recoil = 2f;
                mirror = true;
                faceTarget = true;
                outlineColor = Color.valueOf("1f1c24");

                bullet = new ShrapnelBulletType(){{
                    length = 120f;
                    damage = 20f;
                    width = 2f;
                    serrationLenScl = 2f;
                    serrationSpaceOffset = 6f;
                    serrationFadeOffset = 0f;
                    serrations = 2;
                    serrationWidth = 3f;
                    fromColor = Color.valueOf("8dd9cb");
                    toColor = Color.valueOf("50b2aa");
                    shootEffect = smokeEffect = Fx.sparkShoot;
                    hitColor = Color.valueOf("50b2aa");
                    hitEffect = Fx.hitBulletColor;
                    pierceDamageFactor = 0.8f;
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
            outlineColor = Color.valueOf("0d0d0f");

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
                repairSpeed = 3.5f;
                fractionRepairSpeed = 0.06f;
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