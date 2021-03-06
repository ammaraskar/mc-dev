package net.minecraft.server;

import java.util.List;

public interface IChunkProvider {

    boolean isChunkLoaded(int i, int j);

    Chunk getOrCreateChunk(int i, int j);

    Chunk getChunkAt(int i, int j);

    void getChunkAt(IChunkProvider ichunkprovider, int i, int j);

    boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate);

    boolean unloadChunks();

    boolean canSave();

    List getMobsFor(EnumCreatureType enumcreaturetype, int i, int j, int k);

    ChunkPosition findNearestMapFeature(World world, String s, int i, int j, int k);
}
