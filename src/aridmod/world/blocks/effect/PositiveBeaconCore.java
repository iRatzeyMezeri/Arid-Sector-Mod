package aridmod.world.blocks.effect;

import arc.func.Cons;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.Time;
import arc.util.Tmp;
import aridmod.ASPal;
import aridmod.content.*;
import mindustry.content.*;
import mindustry.entities.Units;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.graphics.*;
import mindustry.logic.*;
import mindustry.type.*;
import mindustry.world.blocks.LaunchAnimator;
import mindustry.world.blocks.storage.*;
import mindustry.world.modules.ItemModule;

import static mindustry.Vars.*;

public class PositiveBeaconCore extends CoreBlock{
    static final float refreshInterval = 6f;
    public float range = 240f;
    public Color baseColor = Pal.heal;
    public float circleSpeed = 300f, circleStroke = 3f;
    public float baseOpacity = 1f;
    public float polyRad = 8f, polySpinScl = 0.8f, polyStroke = 1.5f;
    public int sides = 12;
    public StatusEffect status = AridSectorStatusEffects.safe;
    public float statusDuration = 1500;
    public PositiveBeaconCore(String name) {
        super(name);
        update = true;
        solid = true;
    }


    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);

        Drawf.dashCircle(x * tilesize + offset, y * tilesize + offset, range, Pal.placing);
    }


    public class PositiveBeaconBuild extends CoreBuild implements Ranged, LaunchAnimator{
        public float refresh = Mathf.random(refreshInterval);
        public float warmup = 0f;
        public float totalProgress = 0f;
        public Seq<Unit> targets = new Seq<>();

        @Override
        public void updateTile(){

            if(potentialEfficiency > 0 && (refresh += Time.delta) >= refreshInterval){
                targets.clear();
                refresh = 0f;
                Units.nearby(team, x, y, range, u -> {
                    targets.add(u);
                });
            }

            if(efficiency > 0){
                for(Unit target : targets){
                    target.apply(status, statusDuration * efficiency);
                }
            }

            block.configurable = state.rules.coreBuildAndConfig;
            iframes -= Time.delta;
            thrusterTime -= Time.delta/90f;
            warmup = Mathf.lerp(warmup, efficiency, 0.08f);
            totalProgress += Time.delta / circleSpeed;
        }

        @Override
        public boolean shouldConsume(){
            return targets.size > 0;
        }

        @Override
        public void draw(){
            super.draw();

            if(thrusterTime > 0){
                float frame = thrusterTime;

                Draw.alpha(1f);
                drawThrusters(frame);
                Draw.rect(block.region, x, y);
                Draw.alpha(Interp.pow4In.apply(frame));
                drawThrusters(frame);
                Draw.reset();

                drawTeamTop();
            }else{
                super.draw();
            }

            if(warmup <= 0.001f) return;

            Draw.z(Layer.effect);
            float mod = totalProgress % 1f;
            Draw.color(baseColor.cpy().a(baseOpacity));

            Lines.stroke(circleStroke * (1f - mod) * warmup);
            Lines.poly(x, y, sides, range * mod, mod * 180);

            Lines.stroke(polyStroke);
            Lines.poly(x, y, sides, polyRad * warmup, Time.time / polySpinScl);
            Lines.poly(x, y, sides/2, polyRad/2 * warmup, Time.time / polySpinScl);
            Draw.reset();
        }


        @Override
        public float range(){
            return range;
        }

        @Override
        public float warmup(){
            return warmup;
        }

        @Override
        public void drawSelect(){
            Drawf.dashCircle(x, y, range, Pal.placing);
            if(team.cores().size <= 1 && !proximity.contains(storage -> storage.items == items)) return;

            Lines.stroke(1f, Pal.accent);
            Cons<Building> outline = b -> {
                for(int i = 0; i < 4; i++){
                    Point2 p = Geometry.d8edge[i];
                    float offset = -Math.max(b.block.size - 1, 0) / 2f * tilesize;
                    Draw.rect("block-select", b.x + offset * p.x, b.y + offset * p.y, i * 90);
                }
            };
            team.cores().each(core -> {
                outline.get(core);
                core.proximity.each(storage -> storage.items == items, outline);
            });
            Draw.reset();
        }

        public void drawThrusters(float frame){
            float length = thrusterLength * (frame - 1f) - 1f/4f;
            for(int i = 0; i < 4; i++){
                var reg = i >= 2 ? thruster2 : thruster1;
                float dx = Geometry.d4x[i] * length, dy = Geometry.d4y[i] * length;
                Draw.rect(reg, x + dx, y + dy, i * 90);
            }
        }

        @Override
        public void updateLaunch(){
            float in = renderer.getLandTimeIn() * launchDuration();
            float tsize = Mathf.sample(thrusterSizes, (in + 35f) / launchDuration());

            landParticleTimer += tsize * Time.delta;
            if(landParticleTimer >= 1f){
                tile.getLinkedTiles(t -> {
                    if(Mathf.chance(0.4f)){
                        Fx.coreLandDust.at(t.worldx(), t.worldy(), angleTo(t.worldx(), t.worldy()) + Mathf.range(30f), Tmp.c1.set(t.floor().mapColor).mul(1.5f + Mathf.range(0.15f)));
                    }
                });

                landParticleTimer = 0f;
            }
        }
    }
}
