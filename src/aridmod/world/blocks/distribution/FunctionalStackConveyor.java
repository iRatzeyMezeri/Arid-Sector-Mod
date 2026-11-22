package aridmod.world.blocks.distribution;

import arc.func.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import aridmod.content.AridSectorBlocks;
import mindustry.entities.TargetPriority;
import mindustry.entities.units.*;
import mindustry.input.*;
import mindustry.world.Block;
import mindustry.world.blocks.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.meta.*;

public class FunctionalStackConveyor extends StackConveyor implements Autotiler {
    public @Nullable Block junctionReplacement, bridgeReplacement;

    public FunctionalStackConveyor(String name) {
        super(name);
    }


    @Override
    public void init(){
        super.init();

        if(junctionReplacement == null) junctionReplacement = AridSectorBlocks.roadwayJunction;
        if(bridgeReplacement == null || !(bridgeReplacement instanceof ItemBridge || bridgeReplacement instanceof DuctBridge)) bridgeReplacement = AridSectorBlocks.roadwayBridge;
    }


    @Override
    public Block getReplacement(BuildPlan req, Seq<BuildPlan> plans){
        if(junctionReplacement == null) return this;

        Boolf<Point2> cont = p -> plans.contains(o -> o.x == req.x + p.x && o.y == req.y + p.y && (req.block instanceof StackConveyor || req.block instanceof Junction));
        return cont.get(Geometry.d4(req.rotation)) &&
                cont.get(Geometry.d4(req.rotation - 2)) &&
                req.tile() != null &&
                req.tile().block() instanceof StackConveyor &&
                Mathf.mod(req.tile().build.rotation - req.rotation, 2) == 1 ? junctionReplacement : this;
    }


    @Override
    public void handlePlacementLine(Seq<BuildPlan> plans){
        if(bridgeReplacement == null) return;
        boolean hasJuntionReplacement = junctionReplacement != null;
        if(bridgeReplacement instanceof DuctBridge bridge) Placement.calculateBridges(plans, bridge, hasJuntionReplacement, b -> b instanceof Duct || b instanceof Conveyor);
        if(bridgeReplacement instanceof ItemBridge bridge) Placement.calculateBridges(plans, bridge, hasJuntionReplacement, b -> b instanceof Conveyor);
    }
}
