# Android-MPaaS

MediaPlayer as a Service. Simplify MediaPlayer integration.

## How to use

From source:

1. Clone repository
2. Open AndroidStudio
3. File->New->Import Module...
4. Set source directory to `library` directory of this repository
5. Set module name to `playerservice`
6. Finish
7. Add `compile project(':playerservice')` to dependencies block in your app module's build.gradle
8. Sync now

### API

Bind to `PlayerService`

```java
ServiceConnection conn = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
    }
};

Intent playerServiceIntent = new Intent(this, PlayerService.class)
        .putExtra(PlayerService.EXTRA_USE_EXO_PLAYER, true);

bindService(playerServiceIntent, conn, BIND_AUTO_CREATE);
```

And get `Player`

```java
Player player = PlayerService.getPlayer();
```

You can use `Player` as same as [MediaPlayer](https://developer.android.com/reference/android/media/MediaPlayer.html)

```java
player.prepareFor(uri);
player.start();
player.pause();
player.stop();
```

Finally, unbind from `PlayerService`

```java
unbindService(conn);
```

## Example

```java
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;

import com.eje_c.playerservice.Player;
import com.eje_c.playerservice.PlayerService;

public class MainActivity extends Activity {
    private final ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // Nothing is needed here
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // Nothing is needed here
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent playerServiceIntent = new Intent(this, PlayerService.class)
                .putExtra(PlayerService.EXTRA_USE_EXO_PLAYER, true);

        bindService(playerServiceIntent, conn, BIND_AUTO_CREATE);

        // TODO Init UI
    }

    @Override
    protected void onDestroy() {

        // Unbinding from service releases internal player automatically.
        unbindService(conn);

        super.onDestroy();
    }
}
```
