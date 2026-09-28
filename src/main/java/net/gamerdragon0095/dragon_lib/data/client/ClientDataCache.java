package net.gamerdragon0095.dragon_lib.data.client;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientDataCache {
    // Thread-safe local cache for your HUD/GUI logic
    public static final List<UUID> CLIENT_TRACKED_ENTITIES = new ArrayList<>();
}