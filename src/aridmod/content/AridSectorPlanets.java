package aridmod.content;

import arc.graphics.*;
import mindustry.game.Gamemode;
import aridmod.content.AridSectorBlocks;
import aridmod.planet.AridPlanetGenerator;
import mindustry.content.*;
import mindustry.game.Team;
import mindustry.graphics.g3d.*;
import mindustry.type.*;
import mindustry.world.meta.Env;

public class AridSectorPlanets {
    public static Planet aridsector;

    public static void load(){
        aridsector = new Planet("aridsector", Planets.sun, 2f, 3){{
            generator = new AridPlanetGenerator();

            meshLoader = () -> new HexMesh(this, 5);
            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(this, 621, 2f, 0.13f, 5, Color.valueOf("218a79").a(0.75f), 3, 0.7f, 1f, 0.43f),
                    new HexSkyMesh(this, 49, 2.4f, 0.12f, 5, Color.valueOf("303940").a(0.75f), 3, 0.7f, 1f, 0.45f)
            );

            alwaysUnlocked = true;
            accessible = true;
            allowLaunchToNumbered = false;
            allowLaunchLoadout = false;
            allowSectorInvasion = false;
            startSector = 176;
            clearSectorOnLose = true;
            allowWaves = true;
            prebuildBase = false;
            defaultCore = AridSectorBlocks.coreOccupation;

            defaultEnv = Env.oxygen | Env.terrestrial;

            orbitSpacing = 1;
            drawOrbit = true;
            orbitRadius = 45;
            rotateTime = 24 * 60;

            atmosphereRadIn = 0;
            atmosphereRadOut = 0.15f;
            sectorSeed = 1213;
            bloom = true;
            visible = true;
            landCloudColor = Color.valueOf("218a79");
            atmosphereColor = Color.valueOf("218a79aa");
            iconColor = Color.valueOf("d58181");
            hasAtmosphere = true;

            ruleSetter = r -> {
                r.waveTeam = Team.crux;
//                r.showSpawns = true;
                r.teams.get(r.waveTeam).rtsAi = true;
            };
        }};
    }
}