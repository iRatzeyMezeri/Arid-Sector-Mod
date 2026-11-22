package aridmod.content;

import arc.graphics.*;
import aridmod.ASPal;
import mindustry.game.Gamemode;
import aridmod.content.AridSectorBlocks;
import aridmod.planet.AridPlanetGenerator;
import mindustry.content.*;
import mindustry.game.Team;
import mindustry.graphics.g3d.*;
import mindustry.type.*;
import mindustry.world.meta.Attribute;
import mindustry.world.meta.Env;

public class AridSectorPlanets {
    public static Planet aridsector;

    public static void load(){
        aridsector = new Planet("aridsector", Planets.sun, 1f, 3){{
            generator = new AridPlanetGenerator();

            meshLoader = () -> new HexMesh(this, 6);
            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(this, 621, 2f, 0.13f, 5, Color.valueOf("218a79").a(0.75f), 3, 0.7f, 1f, 0.43f),
                    new HexSkyMesh(this, 49, 2.4f, 0.12f, 5, Color.valueOf("303940").a(0.75f), 3, 0.7f, 1f, 0.45f)
            );

            alwaysUnlocked = true;
            accessible = true;
            allowLaunchToNumbered = true;
            allowLaunchLoadout = false;
            allowSectorInvasion = false;
            startSector = 180;
            clearSectorOnLose = true;
            allowWaves = true;
            prebuildBase = false;
            defaultCore = AridSectorBlocks.coreOccupation;
            defaultEnv = Env.oxygen | Env.terrestrial;

            orbitSpacing = 1;
            drawOrbit = true;
            orbitRadius = 120;
            rotateTime = 24 * 60;

            atmosphereRadIn = 0.02f;
            atmosphereRadOut = 0.3f;
            sectorSeed = 1213;
            bloom = true;
            visible = true;
            landCloudColor = Color.valueOf("218a79");
            atmosphereColor = ASPal.electricDM;
            iconColor = ASPal.earthlyLM;
            hasAtmosphere = true;

            ruleSetter = r -> {
                r.waveTeam = Team.crux;
                r.hideSpawns = false;
                r.allowCoreUnloaders = false;
                r.onlyDepositCore = true;
                r.coreIncinerates = true;
                r.coreCapture = true;
                r.teams.get(r.waveTeam).rtsAi = true;
            };
            unlockedOnLand.add(AridSectorBlocks.coreOccupation);
        }};
    }
}