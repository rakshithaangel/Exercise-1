class MP3Player {
}


interface MediaPlayer {
    void play(String audioType, String fileName);
}


class MP3Adapter implements MediaPlayer {
    private MP3Player mp3Player;

    public MP3Adapter() {
        this.mp3Player = new MP3Player();
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            mp3Player.playMP3(fileName);
        }
    }
}


class MP4Player implements MediaPlayer {
    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp4")) {
            System.out.println("Playing MP4 file: " + fileName);
        }
    }
}


class VLCPlayer implements MediaPlayer {
    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            System.out.println("Playing VLC file: " + fileName);
        }
    }
}


public class AdapterPatternDemo {
    public static void main(String[] args) {
        MediaPlayer mp3Player = new MP3Adapter();
        MediaPlayer mp4Player = new MP4Player();
        MediaPlayer vlcPlayer = new VLCPlayer();

        mp3Player.play("mp3", "song1.mp3");
        mp4Player.play("mp4", "video1.mp4");
        vlcPlayer.play("vlc", "movie1.vlc");
    }
}