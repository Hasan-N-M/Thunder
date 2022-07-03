package network.thunder.core.communication.layer.middle.broadcasting.sync.messages;

import com.google.common.base.Preconditions;
import network.thunder.core.communication.layer.middle.broadcasting.types.ChannelStatusObject;
import network.thunder.core.communication.layer.middle.broadcasting.types.P2PDataObject;
import network.thunder.core.communication.layer.middle.broadcasting.types.PubkeyChannelObject;
import network.thunder.core.communication.layer.middle.broadcasting.types.PubkeyIPObject;

import java.util.ArrayList;
import java.util.List;

public class SyncSendMessage implements Sync {
    public List<PubkeyIPObject> pubkeyIPList;
    public List<PubkeyChannelObject> pubkeyChannelList;
    public List<ChannelStatusObject> channelStatusList;

    public SyncSendMessage (List<P2PDataObject> dataObjects) {
        pubkeyIPList = new ArrayList<>();
        pubkeyChannelList = new ArrayList<>();
        channelStatusList = new ArrayList<>();

        /* ********OpenRefactory Warning********
		 Possible null pointer Dereference!
		 Path: 
			File: SyncProcessorImpl.java, Line: 84
				Message syncSendMessage=messageFactory.getSyncSendMessage(dataObjects);
				 Information is passed through the method call via dataObjects to the formal param dataObjects of the method. This later results into a null pointer dereference.
			File: SyncMessageFactoryImpl.java, Line: 15
				List<P2PDataObject> dataObjects
				Variable dataObjects is declared as a formal parameter.
			File: SyncMessageFactoryImpl.java, Line: 16
				return new SyncSendMessage(dataObjects);
				 Information is passed through the method call via dataObjects to the formal param dataObjects of the method. This later results into a null pointer dereference.
			File: SyncSendMessage.java, Line: 22
				dataObjects
				dataObjects is used in iteration.
				The expression is enclosed inside an Enhanced For statement.
		*/
		for (P2PDataObject obj : dataObjects) {
            if (obj instanceof PubkeyIPObject) {
                pubkeyIPList.add((PubkeyIPObject) obj);

            } else if (obj instanceof PubkeyChannelObject) {
                pubkeyChannelList.add((PubkeyChannelObject) obj);

            } else if (obj instanceof ChannelStatusObject) {
                channelStatusList.add((ChannelStatusObject) obj);

            }
        }
    }

    public List<P2PDataObject> getDataList () {
        List<P2PDataObject> dataList = new ArrayList<>();
        dataList.addAll(pubkeyChannelList);
        dataList.addAll(pubkeyIPList);
        dataList.addAll(channelStatusList);
        return dataList;
    }

    @Override
    public void verify () {
        Preconditions.checkNotNull(pubkeyIPList);
        Preconditions.checkNotNull(pubkeyChannelList);
        Preconditions.checkNotNull(channelStatusList);
    }

    @Override
    public String toString () {
        return "SyncSendMessage{" +
                "pubkeyIPList=" + pubkeyIPList.size() +
                ", pubkeyChannelList=" + pubkeyChannelList.size() +
                ", channelStatusList=" + channelStatusList.size() +
                '}';
    }
}
