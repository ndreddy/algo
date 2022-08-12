import pytest


def filter_nested_items(data_dict):
    flat_dict = {k: v for (k, v) in data_dict.items() if not isinstance(v, dict)}
    return flat_dict


test_data = [

    ({
        "announcementsXml": {},
        "callBackVdn": "Cbseg",
        "offerReason": "OFFER",
        "queueId": 1,
        "queueName": "AFSkill",
        "queueParamsXml": {
            "queue.dialing_prefix": {},
            "callback.app.name": "SimpleOutbound",
            "callback.app.name.agent_first": "SimpleOutbound"
        },
        "queueTimeZone": "America/New_York",
        "queuingVdn": "Seg",
        "state": "OFFERING"
    }

    )
]


@pytest.mark.parametrize("body", test_data)
def test_filter_nested_items(body):
    result = filter_nested_items(body)
    print(str(result))
    assert result
