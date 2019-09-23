-- 这是一个测试脚本
--
-- redis-cli --eval test.lua test:key , 1000 "2"
-- keys *

local times = redis.call('incr', KEYS[1])

if times == 1 then
    redis.call('expire', KEYS[1], ARGV[1])
end
if times > tonumber(ARGV[2]) then
    return 0
end

return 1
