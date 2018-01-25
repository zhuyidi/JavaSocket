package multhreadfiletransport.observer;

/**
 * Created by dela on 1/25/18.
 */
public interface ISectionInfoSpeaker {
    void registerListener(ISectionInfoListener listener);
    void removeListener(ISectionInfoListener listener);
    void sendInfo();
}
